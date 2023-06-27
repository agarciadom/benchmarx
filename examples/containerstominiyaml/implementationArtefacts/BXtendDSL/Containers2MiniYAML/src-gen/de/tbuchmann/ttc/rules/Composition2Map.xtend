package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Data

abstract class Composition2Map extends Elem2Elem {
	new(Containers2MiniYAML trafo) {
		super("Composition2Map", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (c : sourceModel.allContents.filter(typeof(containers.Composition)).toIterable()) {
			_matches += new Source(c)
		}
		
		for (_match : _matches) {
			val c = _match.c
			
			val _corr = wrap(c).updateOrCreateCorrSrc()
			val _mType = new CorrElemType("Map", false)
			val _trg = _corr.getOrCreateTrg(_mType)
			val m = unwrap(_trg.get(0) as SingleElem) as miniyaml.Map
			
			c.getNodes().forEach[corr.assertRuleId("Image2MapEntry", "Container2MapEntry", "Volume2MapEntry")]
			val _nodImaMe = c.getNodes()
					.filter[corr.ruleId == "Image2MapEntry"]
					.map[unwrap(corr.target.get(0) as SingleElem) as miniyaml.MapEntry]
					.toList()
			val _nodConMe = c.getNodes()
					.filter[corr.ruleId == "Container2MapEntry"]
					.map[unwrap(corr.target.get(0) as SingleElem) as miniyaml.MapEntry]
					.toList()
			val _nodVolMe = c.getNodes()
					.filter[corr.ruleId == "Volume2MapEntry"]
					.map[unwrap(corr.target.get(0) as SingleElem) as miniyaml.MapEntry]
					.toList()
			val _entries = entriesFrom(_nodImaMe, _nodConMe, _nodVolMe)
			m.getEntries().clear()
			m.getEntries().addAll(_entries.entries)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	override void onTrgElemCreation(EObject trgElem) {
		switch (trgElem.corrElemPosition) {
			case 0: onMCreation(trgElem as miniyaml.Map)
		}
	}
	def protected abstract void onMCreation(miniyaml.Map m);
	
	override CorrModelDelta targetToSource(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Target>()
		for (m : targetModel.allContents.filter(typeof(miniyaml.Map)).filter[filterM(it)].toIterable()) {
			_matches += new Target(m)
		}
		
		for (_match : _matches) {
			val m = _match.m
			
			val _corr = wrap(m).updateOrCreateCorrTrg()
			val _cType = new CorrElemType("Composition", false)
			val _src = _corr.getOrCreateSrc(_cType)
			val c = unwrap(_src.get(0) as SingleElem) as containers.Composition
			
			//m.getEntries().forEach[corr.assertRuleId("Composition2Map", "Image2MapEntry", "Container2MapEntry", "Volume2MapEntry")]
			val _entImaImg = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Image2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Image]
					.toList()
			val _entConC = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Container2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Container]
					.toList()
			val _entVolV = m.getEntries().filter(typeof(miniyaml.MapEntry)).filter[me | me.key != "version" && me.key != "volumes" && me.key != "services"]
					.filter[corr.ruleId == "Volume2MapEntry"]
					.map[unwrap(corr.source.get(0) as SingleElem) as containers.Volume]
					.toList()
			val _nodes = nodesFrom(m.getEntries(), _entImaImg, _entConC, _entVolV)
			c.getNodes().clear()
			c.getNodes().addAll(_nodes.nodes)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterM(miniyaml.Map m);
	
	@Data protected static class Type4entries {
		List<miniyaml.MapEntry> entries
	}
	def protected abstract Type4entries entriesFrom(List<miniyaml.MapEntry> nodImaMe, List<miniyaml.MapEntry> nodConMe, List<miniyaml.MapEntry> nodVolMe);
	
	@Data protected static class Type4nodes {
		List<containers.Node> nodes
	}
	def protected abstract Type4nodes nodesFrom(List<miniyaml.MapEntry> entries, List<containers.Image> entImaImg, List<containers.Container> entConC, List<containers.Volume> entVolV);
	
	@Data protected static class Source {
		containers.Composition c
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("Composition2Map")
		val c = unwrap(_corr.source.get(0)) as containers.Composition
		return new Source(c)
	}
	@Data protected static class Target {
		miniyaml.Map m
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("Composition2Map")
		val m = unwrap(_corr.target.get(0)) as miniyaml.Map
		return new Target(m)
	}
}
