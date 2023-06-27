package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import containers.Container;
import containers.ContainersFactory;
import containers.Image;
import containers.Node;
import containers.Volume;
import containers.VolumeMount;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.corrmodel.Transformation;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import miniyaml.Map;
import miniyaml.MapEntry;
import miniyaml.MiniyamlFactory;
import miniyaml.Scalar;
import miniyaml.Value;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Composition2MapImpl extends Composition2Map {
  private final MiniyamlFactory yamlFactory = MiniyamlFactory.eINSTANCE;

  public Composition2MapImpl(final Containers2MiniYAML trafo) {
    super(trafo);
  }

  @Override
  protected void onMCreation(final Map m) {
    MapEntry _createMapEntry = this.yamlFactory.createMapEntry();
    final Procedure1<MapEntry> _function = (MapEntry it) -> {
      it.setKey("version");
    };
    final MapEntry m1 = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry, _function);
    MapEntry _createMapEntry_1 = this.yamlFactory.createMapEntry();
    final Procedure1<MapEntry> _function_1 = (MapEntry it) -> {
      it.setKey("services");
    };
    final MapEntry m2 = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry_1, _function_1);
    MapEntry _createMapEntry_2 = this.yamlFactory.createMapEntry();
    final Procedure1<MapEntry> _function_2 = (MapEntry it) -> {
      it.setKey("volumes");
    };
    final MapEntry m3 = ObjectExtensions.<MapEntry>operator_doubleArrow(_createMapEntry_2, _function_2);
    Scalar _createScalar = this.yamlFactory.createScalar();
    final Procedure1<Scalar> _function_3 = (Scalar it) -> {
      it.setValue("2.4");
    };
    Scalar _doubleArrow = ObjectExtensions.<Scalar>operator_doubleArrow(_createScalar, _function_3);
    m1.setValue(_doubleArrow);
    m2.setValue(this.yamlFactory.createMap());
    m3.setValue(this.yamlFactory.createMap());
    m.getEntries().addAll(Collections.<MapEntry>unmodifiableList(CollectionLiterals.<MapEntry>newArrayList(m1, m2, m3)));
  }

  @Override
  protected Composition2Map.Type4entries entriesFrom(final List<MapEntry> nodImaMe, final List<MapEntry> nodConMe, final List<MapEntry> nodVolMe) {
    Composition2Map.Type4entries _xblockexpression = null;
    {
      int _size = IteratorExtensions.size(this.targetModel.getAllContents());
      boolean _equals = (_size == 0);
      if (_equals) {
        ArrayList<MapEntry> _newArrayList = CollectionLiterals.<MapEntry>newArrayList();
        return new Composition2Map.Type4entries(_newArrayList);
      }
      EObject _get = this.targetModel.getContents().get(0);
      final Map root = ((Map) _get);
      final BasicEList<MapEntry> entries = ECollections.<MapEntry>newBasicEList(root.getEntries());
      final Function1<MapEntry, Boolean> _function = (MapEntry e) -> {
        String _key = e.getKey();
        return Boolean.valueOf(Objects.equal(_key, "volumes"));
      };
      final MapEntry volumesEntry = IterableExtensions.<MapEntry>findFirst(entries, _function);
      Value _value = volumesEntry.getValue();
      final Map volumesMap = ((Map) _value);
      volumesMap.getEntries().addAll(nodVolMe);
      final Function1<MapEntry, Boolean> _function_1 = (MapEntry e) -> {
        String _key = e.getKey();
        return Boolean.valueOf(Objects.equal(_key, "services"));
      };
      final MapEntry servicesEntry = IterableExtensions.<MapEntry>findFirst(entries, _function_1);
      Value _value_1 = servicesEntry.getValue();
      final Map servicesMap = ((Map) _value_1);
      servicesMap.getEntries().addAll(nodConMe);
      _xblockexpression = new Composition2Map.Type4entries(entries);
    }
    return _xblockexpression;
  }

  @Override
  protected boolean filterM(final Map m) {
    EObject _eContainer = m.eContainer();
    return (_eContainer == null);
  }

  @Override
  protected Composition2Map.Type4nodes nodesFrom(final List<MapEntry> entries, final List<Image> entImaImg, final List<Container> entConC, final List<Volume> entVolV) {
    final ArrayList<Node> nodes = CollectionLiterals.<Node>newArrayList();
    final ArrayList<Volume> volumeMounts = CollectionLiterals.<Volume>newArrayList();
    EObject _get = this.corrModel.getContents().get(0);
    final Transformation corrRoot = ((Transformation) _get);
    final Function1<Corr, Boolean> _function = (Corr c) -> {
      String _ruleId = c.getRuleId();
      return Boolean.valueOf(Objects.equal(_ruleId, "Volume2MapEntry"));
    };
    final Iterable<Corr> volumeCorrepondences = IterableExtensions.<Corr>filter(corrRoot.getCorrespondences(), _function);
    for (final Corr c : volumeCorrepondences) {
      {
        final EList<CorrElem> src = c.getSource();
        for (final CorrElem ce : src) {
          if ((ce instanceof SingleElem)) {
            EObject _element = ((SingleElem) ce).getElement();
            nodes.add(((Node) _element));
            EObject _element_1 = ((SingleElem) ce).getElement();
            volumeMounts.add(((Volume) _element_1));
          }
        }
      }
    }
    final Function1<Corr, Boolean> _function_1 = (Corr c_1) -> {
      String _ruleId = c_1.getRuleId();
      return Boolean.valueOf(Objects.equal(_ruleId, "Container2MapEntry"));
    };
    final Iterable<Corr> containerCorrepondences = IterableExtensions.<Corr>filter(corrRoot.getCorrespondences(), _function_1);
    final ArrayList<Container> containers = CollectionLiterals.<Container>newArrayList();
    final HashMap<Container, List<String>> dependencies = new HashMap<Container, List<String>>();
    for (final Corr c_1 : containerCorrepondences) {
      {
        final EList<CorrElem> src = c_1.getSource();
        final EList<CorrElem> trg = c_1.getTarget();
        for (final CorrElem ce : src) {
          if ((ce instanceof SingleElem)) {
            EObject _element = ((SingleElem) ce).getElement();
            nodes.add(((Node) _element));
            EObject _element_1 = ((SingleElem) ce).getElement();
            containers.add(((Container) _element_1));
            CorrElem _get_1 = trg.get(0);
            EObject _element_2 = ((SingleElem) _get_1).getElement();
            final MapEntry me = ((MapEntry) _element_2);
            Value _value = me.getValue();
            final Map map = ((Map) _value);
            EList<MapEntry> _entries = map.getEntries();
            for (final MapEntry childEntry : _entries) {
              {
                String _key = childEntry.getKey();
                boolean _equals = Objects.equal(_key, "depends_on");
                if (_equals) {
                  Value _value_1 = childEntry.getValue();
                  final miniyaml.List dependContainers = ((miniyaml.List) _value_1);
                  final ArrayList<String> dependNames = CollectionLiterals.<String>newArrayList();
                  EList<Value> _values = dependContainers.getValues();
                  for (final Value v : _values) {
                    String _value_2 = ((Scalar) v).getValue();
                    dependNames.add(_value_2);
                  }
                  int _size = dependNames.size();
                  boolean _greaterThan = (_size > 0);
                  if (_greaterThan) {
                    EObject _element_3 = ((SingleElem) ce).getElement();
                    dependencies.put(((Container) _element_3), dependNames);
                  }
                }
                String _key_1 = childEntry.getKey();
                boolean _equals_1 = Objects.equal(_key_1, "volumes");
                if (_equals_1) {
                  Value _value_3 = childEntry.getValue();
                  final miniyaml.List mountList = ((miniyaml.List) _value_3);
                  EList<Value> _values_1 = mountList.getValues();
                  for (final Value v_1 : _values_1) {
                    boolean _contains = ((Scalar) v_1).getValue().contains(":");
                    if (_contains) {
                      VolumeMount _createVolumeMount = ContainersFactory.eINSTANCE.createVolumeMount();
                      final Procedure1<VolumeMount> _function_2 = (VolumeMount it) -> {
                        String[] _split = ((Scalar) v_1).getValue().split(":");
                        String _get_2 = null;
                        if (_split!=null) {
                          _get_2=_split[1];
                        }
                        it.setPath(_get_2);
                        final Function1<Volume, Boolean> _function_3 = (Volume it_1) -> {
                          String _name = it_1.getName();
                          String[] _split_1 = ((Scalar) v_1).getValue().split(":");
                          Object _get_3 = null;
                          if (_split_1!=null) {
                            _get_3=_split_1[0];
                          }
                          return Boolean.valueOf(Objects.equal(_name, _get_3));
                        };
                        it.setVolume(IterableExtensions.<Volume>findFirst(volumeMounts, _function_3));
                      };
                      final VolumeMount vm = ObjectExtensions.<VolumeMount>operator_doubleArrow(_createVolumeMount, _function_2);
                      EObject _element_4 = ((SingleElem) ce).getElement();
                      EList<VolumeMount> _volumeMounts = ((Container) _element_4).getVolumeMounts();
                      _volumeMounts.add(vm);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    for (final Container c_2 : containers) {
      {
        final List<String> deps = dependencies.get(c_2);
        if ((deps != null)) {
          for (final String depName : deps) {
            EList<Container> _dependsOn = c_2.getDependsOn();
            final Function1<Container, Boolean> _function_2 = (Container it) -> {
              String _name = it.getName();
              return Boolean.valueOf(Objects.equal(_name, depName));
            };
            Container _findFirst = IterableExtensions.<Container>findFirst(containers, _function_2);
            _dependsOn.add(_findFirst);
          }
        }
      }
    }
    final Function1<Corr, Boolean> _function_2 = (Corr c_3) -> {
      String _ruleId = c_3.getRuleId();
      return Boolean.valueOf(Objects.equal(_ruleId, "Image2MapEntry"));
    };
    final Iterable<Corr> imageCorrespondences = IterableExtensions.<Corr>filter(corrRoot.getCorrespondences(), _function_2);
    for (final Corr c_3 : imageCorrespondences) {
      {
        final EList<CorrElem> src = c_3.getSource();
        final EList<CorrElem> trg = c_3.getTarget();
        for (final CorrElem ce : src) {
          if ((ce instanceof SingleElem)) {
            EObject _element = ((SingleElem) ce).getElement();
            nodes.add(((Node) _element));
            CorrElem _get_1 = trg.get(0);
            EObject _element_1 = ((SingleElem) _get_1).getElement();
            final MapEntry me = ((MapEntry) _element_1);
            final EObject correpondingContainerEntry = me.eContainer().eContainer();
            CorrElem _get_2 = Elem2Elem.elementsToCorr.get(correpondingContainerEntry).getSource().get(0);
            EObject _element_2 = ((SingleElem) _get_2).getElement();
            final Container srcContainer = ((Container) _element_2);
            EObject _element_3 = ((SingleElem) ce).getElement();
            srcContainer.setImage(((Image) _element_3));
          }
        }
      }
    }
    return new Composition2Map.Type4nodes(nodes);
  }
}
