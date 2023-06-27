package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Data

abstract class VolumeMount2Scalar extends Elem2Elem {
	new(Containers2MiniYAML trafo) {
		super("VolumeMount2Scalar", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (vm : sourceModel.allContents.filter(typeof(containers.VolumeMount)).toIterable()) {
			_matches += new Source(vm)
		}
		
		for (_match : _matches) {
			val vm = _match.vm
			
			val _corr = wrap(vm).updateOrCreateCorrSrc()
			val _scType = new CorrElemType("Scalar", false)
			val _trg = _corr.getOrCreateTrg(_scType)
			val sc = unwrap(_trg.get(0) as SingleElem) as miniyaml.Scalar
			
			val _value = valueFrom(vm.getPath(), vm.getVolume())
			sc.setValue(_value.value)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	override CorrModelDelta targetToSource(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Target>()
		for (sc : targetModel.allContents.filter(typeof(miniyaml.Scalar)).filter[filterSc(it)].toIterable()) {
			_matches += new Target(sc)
		}
		
		for (_match : _matches) {
			val sc = _match.sc
			
			val _corr = wrap(sc).updateOrCreateCorrTrg()
			val _vmType = new CorrElemType("VolumeMount", false)
			_corr.getOrCreateSrc(_vmType)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterSc(miniyaml.Scalar sc);
	
	@Data protected static class Type4value {
		String value
	}
	def protected abstract Type4value valueFrom(String path, containers.Volume volume);
	
	@Data protected static class Source {
		containers.VolumeMount vm
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("VolumeMount2Scalar")
		val vm = unwrap(_corr.source.get(0)) as containers.VolumeMount
		return new Source(vm)
	}
	@Data protected static class Target {
		miniyaml.Scalar sc
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("VolumeMount2Scalar")
		val sc = unwrap(_corr.target.get(0)) as miniyaml.Scalar
		return new Target(sc)
	}
}
