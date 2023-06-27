package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import containers.Volume;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import miniyaml.MapEntry;
import miniyaml.Scalar;

@SuppressWarnings("all")
public class VolumeMount2ScalarImpl extends VolumeMount2Scalar {
  public VolumeMount2ScalarImpl(final Containers2MiniYAML trafo) {
    super(trafo);
  }

  @Override
  protected VolumeMount2Scalar.Type4value valueFrom(final String path, final Volume volume) {
    String _name = volume.getName();
    String _plus = (_name + ":");
    String _plus_1 = (_plus + path);
    return new VolumeMount2Scalar.Type4value(_plus_1);
  }

  @Override
  protected boolean filterSc(final Scalar sc) {
    return (!((sc.eContainer() instanceof MapEntry) && Objects.equal(((MapEntry) sc.eContainer()).getKey(), "version")));
  }
}
