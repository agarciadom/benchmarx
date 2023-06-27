package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Data

abstract class Volume2MapEntry extends Elem2Elem {
	new(Containers2MiniYAML trafo) {
		super("Volume2MapEntry", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (v : sourceModel.allContents.filter(typeof(containers.Volume)).toIterable()) {
			_matches += new Source(v)
		}
		
		for (_match : _matches) {
			val v = _match.v
			
			val _corr = wrap(v).updateOrCreateCorrSrc()
			val _meType = new CorrElemType("MapEntry", false)
			val _trg = _corr.getOrCreateTrg(_meType)
			val me = unwrap(_trg.get(0) as SingleElem) as miniyaml.MapEntry
			
			me.setKey(v.getName())
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	override CorrModelDelta targetToSource(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Target>()
		for (me : targetModel.allContents.filter(typeof(miniyaml.MapEntry)).filter[filterMe(it)].toIterable()) {
			_matches += new Target(me)
		}
		
		for (_match : _matches) {
			val me = _match.me
			
			val _corr = wrap(me).updateOrCreateCorrTrg()
			val _vType = new CorrElemType("Volume", false)
			val _src = _corr.getOrCreateSrc(_vType)
			val v = unwrap(_src.get(0) as SingleElem) as containers.Volume
			
			v.setName(me.getKey())
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterMe(miniyaml.MapEntry me);
	
	@Data protected static class Source {
		containers.Volume v
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("Volume2MapEntry")
		val v = unwrap(_corr.source.get(0)) as containers.Volume
		return new Source(v)
	}
	@Data protected static class Target {
		miniyaml.MapEntry me
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("Volume2MapEntry")
		val me = unwrap(_corr.target.get(0)) as miniyaml.MapEntry
		return new Target(me)
	}
}
