package de.tbuchmann.ttc.rules;

import com.google.common.collect.Iterators;
import containers.Volume;
import containers.VolumeMount;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import miniyaml.Scalar;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public abstract class VolumeMount2Scalar extends Elem2Elem {
  @Data
  protected static class Type4value {
    private final String value;

    public Type4value(final String value) {
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
      VolumeMount2Scalar.Type4value other = (VolumeMount2Scalar.Type4value) obj;
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
    public String getValue() {
      return this.value;
    }
  }

  @Data
  protected static class Source {
    private final VolumeMount vm;

    public Source(final VolumeMount vm) {
      super();
      this.vm = vm;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.vm== null) ? 0 : this.vm.hashCode());
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
      VolumeMount2Scalar.Source other = (VolumeMount2Scalar.Source) obj;
      if (this.vm == null) {
        if (other.vm != null)
          return false;
      } else if (!this.vm.equals(other.vm))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("vm", this.vm);
      return b.toString();
    }

    @Pure
    public VolumeMount getVm() {
      return this.vm;
    }
  }

  @Data
  protected static class Target {
    private final Scalar sc;

    public Target(final Scalar sc) {
      super();
      this.sc = sc;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.sc== null) ? 0 : this.sc.hashCode());
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
      VolumeMount2Scalar.Target other = (VolumeMount2Scalar.Target) obj;
      if (this.sc == null) {
        if (other.sc != null)
          return false;
      } else if (!this.sc.equals(other.sc))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("sc", this.sc);
      return b.toString();
    }

    @Pure
    public Scalar getSc() {
      return this.sc;
    }
  }

  public VolumeMount2Scalar(final Containers2MiniYAML trafo) {
    super("VolumeMount2Scalar", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<VolumeMount2Scalar.Source> _matches = new ArrayList<VolumeMount2Scalar.Source>();
    Iterable<VolumeMount> _iterable = IteratorExtensions.<VolumeMount>toIterable(Iterators.<VolumeMount>filter(this.sourceModel.getAllContents(), VolumeMount.class));
    for (final VolumeMount vm : _iterable) {
      VolumeMount2Scalar.Source _source = new VolumeMount2Scalar.Source(vm);
      _matches.add(_source);
    }
    for (final VolumeMount2Scalar.Source _match : _matches) {
      {
        final VolumeMount vm_1 = _match.vm;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(vm_1));
        final Elem2Elem.CorrElemType _scType = new Elem2Elem.CorrElemType("Scalar", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _scType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Scalar sc = ((Scalar) _unwrap);
        final VolumeMount2Scalar.Type4value _value = this.valueFrom(vm_1.getPath(), vm_1.getVolume());
        sc.setValue(_value.value);
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
    final ArrayList<VolumeMount2Scalar.Target> _matches = new ArrayList<VolumeMount2Scalar.Target>();
    final Function1<Scalar, Boolean> _function = (Scalar it) -> {
      return Boolean.valueOf(this.filterSc(it));
    };
    Iterable<Scalar> _iterable = IteratorExtensions.<Scalar>toIterable(IteratorExtensions.<Scalar>filter(Iterators.<Scalar>filter(this.targetModel.getAllContents(), Scalar.class), _function));
    for (final Scalar sc : _iterable) {
      VolumeMount2Scalar.Target _target = new VolumeMount2Scalar.Target(sc);
      _matches.add(_target);
    }
    for (final VolumeMount2Scalar.Target _match : _matches) {
      {
        final Scalar sc_1 = _match.sc;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(sc_1));
        final Elem2Elem.CorrElemType _vmType = new Elem2Elem.CorrElemType("VolumeMount", false);
        this.getOrCreateSrc(_corr, _vmType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterSc(final Scalar sc);

  protected abstract VolumeMount2Scalar.Type4value valueFrom(final String path, final Volume volume);

  protected static VolumeMount2Scalar.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "VolumeMount2Scalar");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final VolumeMount vm = ((VolumeMount) _unwrap);
    return new VolumeMount2Scalar.Source(vm);
  }

  protected static VolumeMount2Scalar.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "VolumeMount2Scalar");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Scalar sc = ((Scalar) _unwrap);
    return new VolumeMount2Scalar.Target(sc);
  }
}
