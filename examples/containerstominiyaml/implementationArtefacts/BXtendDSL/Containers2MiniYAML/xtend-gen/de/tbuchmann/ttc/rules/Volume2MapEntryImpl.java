package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import miniyaml.Map;
import miniyaml.MapEntry;

@SuppressWarnings("all")
public class Volume2MapEntryImpl extends Volume2MapEntry {
  public Volume2MapEntryImpl(final Containers2MiniYAML trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterMe(final MapEntry me) {
    return ((((((!Objects.equal(me.getKey(), "services")) || (!Objects.equal(me.getKey(), "version"))) || (!Objects.equal(me.getKey(), "volumes"))) && 
      (me.eContainer() instanceof Map)) && 
      (me.eContainer().eContainer() instanceof MapEntry)) && 
      Objects.equal(((MapEntry) me.eContainer().eContainer()).getKey(), "volumes"));
  }
}
