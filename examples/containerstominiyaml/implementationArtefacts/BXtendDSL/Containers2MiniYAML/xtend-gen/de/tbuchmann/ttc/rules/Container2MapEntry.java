package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import containers.Container;
import containers.Image;
import containers.VolumeMount;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import miniyaml.MapEntry;
import miniyaml.Scalar;
import miniyaml.Value;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public abstract class Container2MapEntry extends Elem2Elem {
  @Data
  protected static class Type4value {
    private final Value value;

    public Type4value(final Value value) {
      super();
      this.value = value;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.value== null) ? 0 : this.value.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Container2MapEntry.Type4value other = (Container2MapEntry.Type4value) obj;
      if (this.value == null) {
        if (other.value != null)
          return false;
      } else if (!this.value.equals(other.value))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("value", this.value);
      return b.toString();
    }

    @Pure
    public Value getValue() {
      return this.value;
    }
  }

  @Data
  protected static class Type4image_replicas_dependsOn {
    private final Image image;

    private final int replicas;

    private final List<Container> dependsOn;

    public Type4image_replicas_dependsOn(final Image image, final int replicas, final List<Container> dependsOn) {
      super();
      this.image = image;
      this.replicas = replicas;
      this.dependsOn = dependsOn;
    }

    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.image== null) ? 0 : this.image.hashCode());
      result = prime * result + this.replicas;
      return prime * result + ((this.dependsOn== null) ? 0 : this.dependsOn.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Container2MapEntry.Type4image_replicas_dependsOn other = (Container2MapEntry.Type4image_replicas_dependsOn) obj;
      if (this.image == null) {
        if (other.image != null)
          return false;
      } else if (!this.image.equals(other.image))
        return false;
      if (other.replicas != this.replicas)
        return false;
      if (this.dependsOn == null) {
        if (other.dependsOn != null)
          return false;
      } else if (!this.dependsOn.equals(other.dependsOn))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("image", this.image);
      b.add("replicas", this.replicas);
      b.add("dependsOn", this.dependsOn);
      return b.toString();
    }

    @Pure
    public Image getImage() {
      return this.image;
    }

    @Pure
    public int getReplicas() {
      return this.replicas;
    }

    @Pure
    public List<Container> getDependsOn() {
      return this.dependsOn;
    }
  }

  @Data
  protected static class Source {
    private final Container c;

    public Source(final Container c) {
      super();
      this.c = c;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.c== null) ? 0 : this.c.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Container2MapEntry.Source other = (Container2MapEntry.Source) obj;
      if (this.c == null) {
        if (other.c != null)
          return false;
      } else if (!this.c.equals(other.c))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("c", this.c);
      return b.toString();
    }

    @Pure
    public Container getC() {
      return this.c;
    }
  }

  @Data
  protected static class Target {
    private final MapEntry me;

    public Target(final MapEntry me) {
      super();
      this.me = me;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.me== null) ? 0 : this.me.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Container2MapEntry.Target other = (Container2MapEntry.Target) obj;
      if (this.me == null) {
        if (other.me != null)
          return false;
      } else if (!this.me.equals(other.me))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("me", this.me);
      return b.toString();
    }

    @Pure
    public MapEntry getMe() {
      return this.me;
    }
  }

  public Container2MapEntry(final Containers2MiniYAML trafo) {
    super("Container2MapEntry", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Container2MapEntry.Source> _matches = new ArrayList<Container2MapEntry.Source>();
    Iterable<Container> _iterable = IteratorExtensions.<Container>toIterable(Iterators.<Container>filter(this.sourceModel.getAllContents(), Container.class));
    for (final Container c : _iterable) {
      Container2MapEntry.Source _source = new Container2MapEntry.Source(c);
      _matches.add(_source);
    }
    for (final Container2MapEntry.Source _match : _matches) {
      {
        final Container c_1 = _match.c;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(c_1));
        final Elem2Elem.CorrElemType _meType = new Elem2Elem.CorrElemType("MapEntry", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _meType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final MapEntry me = ((MapEntry) _unwrap);
        me.setKey(c_1.getName());
        final Consumer<VolumeMount> _function = (VolumeMount it) -> {
          Elem2Elem.assertRuleId(this.getCorr(it), "VolumeMount2Scalar");
        };
        c_1.getVolumeMounts().forEach(_function);
        final Function1<VolumeMount, Boolean> _function_1 = (VolumeMount it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "VolumeMount2Scalar"));
        };
        final Function1<VolumeMount, Scalar> _function_2 = (VolumeMount it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Scalar) _unwrap_1);
        };
        final List<Scalar> _volSc = IterableExtensions.<Scalar>toList(IterableExtensions.<VolumeMount, Scalar>map(IterableExtensions.<VolumeMount>filter(c_1.getVolumeMounts(), _function_1), _function_2));
        final Container2MapEntry.Type4value _value = this.valueFrom(c_1.getImage(), c_1.getReplicas(), c_1.getDependsOn(), _volSc, me.getValue());
        me.setValue(_value.value);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Container2MapEntry.Target> _matches = new ArrayList<Container2MapEntry.Target>();
    final Function1<MapEntry, Boolean> _function = (MapEntry it) -> {
      return Boolean.valueOf(this.filterMe(it));
    };
    Iterable<MapEntry> _iterable = IteratorExtensions.<MapEntry>toIterable(IteratorExtensions.<MapEntry>filter(Iterators.<MapEntry>filter(this.targetModel.getAllContents(), MapEntry.class), _function));
    for (final MapEntry me : _iterable) {
      Container2MapEntry.Target _target = new Container2MapEntry.Target(me);
      _matches.add(_target);
    }
    for (final Container2MapEntry.Target _match : _matches) {
      {
        final MapEntry me_1 = _match.me;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(me_1));
        final Elem2Elem.CorrElemType _cType = new Elem2Elem.CorrElemType("Container", false);
        final List<CorrElem> _src = this.getOrCreateSrc(_corr, _cType);
        CorrElem _get = _src.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Container c = ((Container) _unwrap);
        c.setName(me_1.getKey());
        final Container2MapEntry.Type4image_replicas_dependsOn _image_replicas_dependsOn = this.image_replicas_dependsOnFrom(me_1.getValue());
        c.setImage(_image_replicas_dependsOn.image);
        c.setReplicas(_image_replicas_dependsOn.replicas);
        c.getDependsOn().clear();
        c.getDependsOn().addAll(_image_replicas_dependsOn.dependsOn);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterMe(final MapEntry me);

  protected abstract Container2MapEntry.Type4value valueFrom(final Image image, final int replicas, final List<Container> dependsOn, final List<Scalar> volSc, final Value oldValue);

  protected abstract Container2MapEntry.Type4image_replicas_dependsOn image_replicas_dependsOnFrom(final Value value);

  protected static Container2MapEntry.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Container2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Container c = ((Container) _unwrap);
    return new Container2MapEntry.Source(c);
  }

  protected static Container2MapEntry.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Container2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final MapEntry me = ((MapEntry) _unwrap);
    return new Container2MapEntry.Target(me);
  }
}
