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

abstract class Container2MapEntry extends Elem2Elem {
	new(Containers2MiniYAML trafo) {
		super("Container2MapEntry", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (c : sourceModel.allContents.filter(typeof(containers.Container)).toIterable()) {
			_matches += new Source(c)
		}
		
		for (_match : _matches) {
			val c = _match.c
			
			val _corr = wrap(c).updateOrCreateCorrSrc()
			val _meType = new CorrElemType("MapEntry", false)
			val _trg = _corr.getOrCreateTrg(_meType)
			val me = unwrap(_trg.get(0) as SingleElem) as miniyaml.MapEntry
			
			me.setKey(c.getName())
			
			c.getVolumeMounts().forEach[corr.assertRuleId("VolumeMount2Scalar")]
			val _volSc = c.getVolumeMounts()
					.filter[corr.ruleId == "VolumeMount2Scalar"]
					.map[unwrap(corr.target.get(0) as SingleElem) as miniyaml.Scalar]
					.toList()
			val _value = valueFrom(c.getImage(), c.getReplicas(), c.getDependsOn(), _volSc, me.value)
			me.setValue(_value.value)
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
			val _cType = new CorrElemType("Container", false)
			val _src = _corr.getOrCreateSrc(_cType)
			val c = unwrap(_src.get(0) as SingleElem) as containers.Container
			
			c.setName(me.getKey())
			
			val _image_replicas_dependsOn = image_replicas_dependsOnFrom(me.getValue())
			c.setImage(_image_replicas_dependsOn.image)
			c.setReplicas(_image_replicas_dependsOn.replicas)
			c.getDependsOn().clear()
			c.getDependsOn().addAll(_image_replicas_dependsOn.dependsOn)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterMe(miniyaml.MapEntry me);
	
	@Data protected static class Type4value {
		miniyaml.Value value
	}
	def protected abstract Type4value valueFrom(containers.Image image, int replicas, List<containers.Container> dependsOn, List<miniyaml.Scalar> volSc, miniyaml.Value oldValue);
	
	@Data protected static class Type4image_replicas_dependsOn {
		containers.Image image
		int replicas
		List<containers.Container> dependsOn
	}
	def protected abstract Type4image_replicas_dependsOn image_replicas_dependsOnFrom(miniyaml.Value value);
	
	@Data protected static class Source {
		containers.Container c
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("Container2MapEntry")
		val c = unwrap(_corr.source.get(0)) as containers.Container
		return new Source(c)
	}
	@Data protected static class Target {
		miniyaml.MapEntry me
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("Container2MapEntry")
		val me = unwrap(_corr.target.get(0)) as miniyaml.MapEntry
		return new Target(me)
	}
}
