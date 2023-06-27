package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.trafo.Containers2MiniYAML
import java.util.ArrayList
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Data

abstract class Image2MapEntry extends Elem2Elem {
	new(Containers2MiniYAML trafo) {
		super("Image2MapEntry", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (img : sourceModel.allContents.filter(typeof(containers.Image)).toIterable()) {
			_matches += new Source(img)
		}
		
		for (_match : _matches) {
			val img = _match.img
			
			val _corr = wrap(img).updateOrCreateCorrSrc()
			val _meType = new CorrElemType("MapEntry", false)
			val _trg = _corr.getOrCreateTrg(_meType)
			val me = unwrap(_trg.get(0) as SingleElem) as miniyaml.MapEntry
			
			val _value = valueFrom(img.getImage())
			me.setValue(_value.value)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	override void onTrgElemCreation(EObject trgElem) {
		switch (trgElem.corrElemPosition) {
			case 0: onMeCreation(trgElem as miniyaml.MapEntry)
		}
	}
	def protected abstract void onMeCreation(miniyaml.MapEntry me);
	
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
			val _imgType = new CorrElemType("Image", false)
			val _src = _corr.getOrCreateSrc(_imgType)
			val img = unwrap(_src.get(0) as SingleElem) as containers.Image
			
			val _image = imageFrom(me.getValue())
			img.setImage(_image.image)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterMe(miniyaml.MapEntry me);
	
	@Data protected static class Type4value {
		miniyaml.Value value
	}
	def protected abstract Type4value valueFrom(String image);
	
	@Data protected static class Type4image {
		String image
	}
	def protected abstract Type4image imageFrom(miniyaml.Value value);
	
	@Data protected static class Source {
		containers.Image img
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("Image2MapEntry")
		val img = unwrap(_corr.source.get(0)) as containers.Image
		return new Source(img)
	}
	@Data protected static class Target {
		miniyaml.MapEntry me
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("Image2MapEntry")
		val me = unwrap(_corr.target.get(0)) as miniyaml.MapEntry
		return new Target(me)
	}
}
