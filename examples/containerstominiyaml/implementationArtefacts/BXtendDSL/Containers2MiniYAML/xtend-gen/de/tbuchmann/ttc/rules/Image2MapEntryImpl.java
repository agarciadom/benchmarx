package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.Scalar;
import miniyaml.Value;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Image2MapEntryImpl extends Image2MapEntry {
  public Image2MapEntryImpl(final Containers2MiniYAML trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterMe(final MapEntry me) {
    String _key = me.getKey();
    return Objects.equal(_key, "image");
  }

  @Override
  protected Image2MapEntry.Type4value valueFrom(final String image) {
    Scalar _createScalar = MiniyamlFactory.eINSTANCE.createScalar();
    final Procedure1<Scalar> _function = (Scalar it) -> {
      it.setValue(image);
    };
    Scalar _doubleArrow = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar, _function);
    return new Image2MapEntry.Type4value(_doubleArrow);
  }

  @Override
  protected Image2MapEntry.Type4image imageFrom(final Value value) {
    String _value = ((Scalar) value).getValue();
    return new Image2MapEntry.Type4image(_value);
  }

  @Override
  protected void onMeCreation(final MapEntry me) {
    me.setKey("image");
  }
}
