package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import containers.Container;
import containers.Image;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.Scalar;
import miniyaml.Value;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Container2MapEntryImpl extends Container2MapEntry {
  private final MiniyamlFactory yamlFactory = MiniyamlFactory.eINSTANCE;

  public Container2MapEntryImpl(final Containers2MiniYAML trafo) {
    super(trafo);
  }

  @Override
  protected Container2MapEntry.Type4value valueFrom(final Image image, final int replicas, final List<Container> dependsOn, final List<Scalar> volSc, final Value oldValue) {
    Container2MapEntry.Type4value _xblockexpression = null;
    {
      Map entry = this.yamlFactory.createMap();
      if ((image != null)) {
        EList<MapEntry> _entries = entry.getEntries();
        CorrElem _get = Elem2Elem.elementsToCorr.get(image).getTarget().get(0);
        EObject _element = ((SingleElem) _get).getElement();
        _entries.add(((MapEntry) _element));
      }
      boolean _isEmpty = dependsOn.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        final MapEntry me = this.createMapEntry("depends_on");
        Value _value = me.getValue();
        final miniyaml.List list = ((miniyaml.List) _value);
        for (final Container c : dependsOn) {
          EList<Value> _values = list.getValues();
          Scalar _createScalar = this.yamlFactory.createScalar();
          final Procedure1<Scalar> _function = (Scalar s) -> {
            s.setValue(c.getName());
          };
          Scalar _doubleArrow = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar, _function);
          _values.add(_doubleArrow);
        }
        EList<MapEntry> _entries_1 = entry.getEntries();
        _entries_1.add(me);
      }
      if ((replicas > 1)) {
        MapEntry _createMapEntry = this.yamlFactory.createMapEntry();
        final Procedure1<MapEntry> _function_1 = (MapEntry it) -> {
          it.setKey("replicas");
          Scalar _createScalar_1 = this.yamlFactory.createScalar();
          final Procedure1<Scalar> _function_2 = (Scalar it_1) -> {
            it_1.setValue(("" + Integer.valueOf(replicas)));
          };
          Scalar _doubleArrow_1 = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar_1, _function_2);
          it.setValue(_doubleArrow_1);
        };
        final MapEntry me_1 = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry, _function_1);
        EList<MapEntry> _entries_2 = entry.getEntries();
        _entries_2.add(me_1);
      }
      boolean _isEmpty_1 = volSc.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        final MapEntry me_2 = this.createMapEntry("volumes");
        Value _value_1 = me_2.getValue();
        final miniyaml.List list_1 = ((miniyaml.List) _value_1);
        for (final Scalar s : volSc) {
          EList<Value> _values_1 = list_1.getValues();
          _values_1.add(s);
        }
        EList<MapEntry> _entries_3 = entry.getEntries();
        _entries_3.add(me_2);
      }
      if (((!Objects.equal(oldValue, null)) && (oldValue instanceof Map))) {
        EList<MapEntry> _entries_4 = ((Map) oldValue).getEntries();
        for (final MapEntry me_3 : _entries_4) {
          {
            String _key = me_3.getKey();
            boolean _equals = Objects.equal(_key, "restart");
            if (_equals) {
              MapEntry _createMapEntry_1 = this.yamlFactory.createMapEntry();
              final Procedure1<MapEntry> _function_2 = (MapEntry it) -> {
                it.setKey(me_3.getKey());
              };
              final MapEntry newME = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry_1, _function_2);
              Scalar _createScalar_1 = this.yamlFactory.createScalar();
              final Procedure1<Scalar> _function_3 = (Scalar it) -> {
                Value _value_2 = me_3.getValue();
                it.setValue(((Scalar) _value_2).getValue());
              };
              final Scalar newVal = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar_1, _function_3);
              newME.setValue(newVal);
              EList<MapEntry> _entries_5 = entry.getEntries();
              _entries_5.add(newME);
            }
            String _key_1 = me_3.getKey();
            boolean _equals_1 = Objects.equal(_key_1, "tmpfs");
            if (_equals_1) {
              MapEntry _createMapEntry_2 = this.yamlFactory.createMapEntry();
              final Procedure1<MapEntry> _function_4 = (MapEntry it) -> {
                it.setKey(me_3.getKey());
              };
              final MapEntry newME_1 = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry_2, _function_4);
              final miniyaml.List newList = this.yamlFactory.createList();
              newME_1.setValue(newList);
              final Value oldMEValue = me_3.getValue();
              EList<Value> _values_2 = ((miniyaml.List) oldMEValue).getValues();
              for (final Value v : _values_2) {
                {
                  Scalar _createScalar_2 = this.yamlFactory.createScalar();
                  final Procedure1<Scalar> _function_5 = (Scalar it) -> {
                    it.setValue(((Scalar) v).getValue());
                  };
                  final Scalar newV = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar_2, _function_5);
                  EList<Value> _values_3 = newList.getValues();
                  _values_3.add(newV);
                }
              }
              EList<MapEntry> _entries_6 = entry.getEntries();
              _entries_6.add(newME_1);
            }
          }
        }
      }
      _xblockexpression = new Container2MapEntry.Type4value(entry);
    }
    return _xblockexpression;
  }

  @Override
  protected boolean filterMe(final MapEntry me) {
    return ((((((!Objects.equal(me.getKey(), "services")) || (!Objects.equal(me.getKey(), "version"))) || (!Objects.equal(me.getKey(), "volumes"))) && 
      (me.eContainer() instanceof Map)) && 
      (me.eContainer().eContainer() instanceof MapEntry)) && 
      Objects.equal(((MapEntry) me.eContainer().eContainer()).getKey(), "services"));
  }

  @Override
  protected Container2MapEntry.Type4image_replicas_dependsOn image_replicas_dependsOnFrom(final Value value) {
    final ArrayList<Container> depends = CollectionLiterals.<Container>newArrayList();
    int replicas = 1;
    if ((value instanceof Map)) {
      EList<MapEntry> _entries = ((Map) value).getEntries();
      for (final MapEntry me : _entries) {
        String _key = me.getKey();
        boolean _equals = Objects.equal(_key, "replicas");
        if (_equals) {
          Value _value = me.getValue();
          replicas = Integer.parseInt(((Scalar) _value).getValue());
        }
      }
    }
    return new Container2MapEntry.Type4image_replicas_dependsOn(null, replicas, depends);
  }

  private MapEntry createMapEntry(final String name) {
    MapEntry _createMapEntry = this.yamlFactory.createMapEntry();
    final Procedure1<MapEntry> _function = (MapEntry m) -> {
      m.setKey(name);
    };
    final MapEntry me = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry, _function);
    final miniyaml.List list = this.yamlFactory.createList();
    me.setValue(list);
    return me;
  }
}
