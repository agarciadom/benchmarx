package de.tbuchmann.ttc.trafo;

import de.tbuchmann.ttc.rules.Composition2MapImpl;
import de.tbuchmann.ttc.rules.Container2MapEntryImpl;
import de.tbuchmann.ttc.rules.Elem2Elem;
import de.tbuchmann.ttc.rules.Image2MapEntryImpl;
import de.tbuchmann.ttc.rules.Volume2MapEntryImpl;
import de.tbuchmann.ttc.rules.VolumeMount2ScalarImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class Containers2MiniYAML extends AbstractContainers2MiniYAML {
  private static final Set<Object> options = Collections.<Object>unmodifiableSet(CollectionLiterals.<Object>newHashSet());

  private final HashMap<String, Object> configuration = new HashMap<String, Object>();

  public Containers2MiniYAML() {
    super();
  }

  public Containers2MiniYAML(final Resource source, final Resource target, final Resource correspondence) {
    super(source, target, correspondence);
  }

  @Override
  public Object getOption(final String option) {
    boolean _contains = Containers2MiniYAML.options.contains(option);
    boolean _not = (!_contains);
    if (_not) {
      throw new IllegalArgumentException((((("Invalid option \'" + option) + "\'! Valid options are ") + Containers2MiniYAML.options) + "."));
    }
    return this.configuration.get(option);
  }

  @Override
  public void setOption(final String option, final Object value) {
    boolean _contains = Containers2MiniYAML.options.contains(option);
    boolean _not = (!_contains);
    if (_not) {
      throw new IllegalArgumentException((((("Invalid option \'" + option) + "\'! Valid options are ") + Containers2MiniYAML.options) + "."));
    }
    this.configuration.put(option, value);
  }

  @Override
  protected List<Elem2Elem> createRules() {
    ArrayList<Elem2Elem> result = new ArrayList<Elem2Elem>();
    Volume2MapEntryImpl _volume2MapEntryImpl = new Volume2MapEntryImpl(this);
    result.add(_volume2MapEntryImpl);
    Image2MapEntryImpl _image2MapEntryImpl = new Image2MapEntryImpl(this);
    result.add(_image2MapEntryImpl);
    VolumeMount2ScalarImpl _volumeMount2ScalarImpl = new VolumeMount2ScalarImpl(this);
    result.add(_volumeMount2ScalarImpl);
    Container2MapEntryImpl _container2MapEntryImpl = new Container2MapEntryImpl(this);
    result.add(_container2MapEntryImpl);
    Composition2MapImpl _composition2MapImpl = new Composition2MapImpl(this);
    result.add(_composition2MapImpl);
    return result;
  }
}
