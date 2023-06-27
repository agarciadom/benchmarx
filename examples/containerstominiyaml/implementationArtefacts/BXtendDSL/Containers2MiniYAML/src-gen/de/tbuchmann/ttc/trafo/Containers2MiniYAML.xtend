package de.tbuchmann.ttc.trafo

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.rules.Volume2MapEntryImpl
import de.tbuchmann.ttc.rules.Image2MapEntryImpl
import de.tbuchmann.ttc.rules.VolumeMount2ScalarImpl
import de.tbuchmann.ttc.rules.Container2MapEntryImpl
import de.tbuchmann.ttc.rules.Composition2MapImpl

class Containers2MiniYAML extends AbstractContainers2MiniYAML {
	static val options = #{}
	
	val configuration = new HashMap<String, Object>()
	
	new() {
		super()
	}
	new(Resource source, Resource target, Resource correspondence) {
		super(source, target, correspondence)
	}
	
	override Object getOption(String option) {
		if (!options.contains(option)) {
			throw new IllegalArgumentException("Invalid option '" + option + "'! Valid options are " + options + ".")
		}
		return configuration.get(option)
	}
	override void setOption(String option, Object value) {
		if (!options.contains(option)) {
			throw new IllegalArgumentException("Invalid option '" + option + "'! Valid options are " + options + ".")
		}
		configuration.put(option, value)
	}
	
	override protected List<Elem2Elem> createRules() {
		var result = new ArrayList<Elem2Elem>()
		result += new Volume2MapEntryImpl(this)
		result += new Image2MapEntryImpl(this)
		result += new VolumeMount2ScalarImpl(this)
		result += new Container2MapEntryImpl(this)
		result += new Composition2MapImpl(this)
		return result
	}
}
