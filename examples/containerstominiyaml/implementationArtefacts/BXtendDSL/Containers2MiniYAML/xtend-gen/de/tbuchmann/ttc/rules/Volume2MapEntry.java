package de.tbuchmann.ttc.rules;

import com.google.common.collect.Iterators;
import containers.Volume;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import miniyaml.MapEntry;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public abstract class Volume2MapEntry extends Elem2Elem {
  @Data
  protected static class Source {
    private final Volume v;

    public Source(final Volume v) {
      super();
      this.v = v;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.v== null) ? 0 : this.v.hashCode());
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
      Volume2MapEntry.Source other = (Volume2MapEntry.Source) obj;
      if (this.v == null) {
        if (other.v != null)
          return false;
      } else if (!this.v.equals(other.v))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("v", this.v);
      return b.toString();
    }

    @Pure
    public Volume getV() {
      return this.v;
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
      Volume2MapEntry.Target other = (Volume2MapEntry.Target) obj;
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

  public Volume2MapEntry(final Containers2MiniYAML trafo) {
    super("Volume2MapEntry", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Volume2MapEntry.Source> _matches = new ArrayList<Volume2MapEntry.Source>();
    Iterable<Volume> _iterable = IteratorExtensions.<Volume>toIterable(Iterators.<Volume>filter(this.sourceModel.getAllContents(), Volume.class));
    for (final Volume v : _iterable) {
      Volume2MapEntry.Source _source = new Volume2MapEntry.Source(v);
      _matches.add(_source);
    }
    for (final Volume2MapEntry.Source _match : _matches) {
      {
        final Volume v_1 = _match.v;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(v_1));
        final Elem2Elem.CorrElemType _meType = new Elem2Elem.CorrElemType("MapEntry", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _meType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final MapEntry me = ((MapEntry) _unwrap);
        me.setKey(v_1.getName());
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
    final ArrayList<Volume2MapEntry.Target> _matches = new ArrayList<Volume2MapEntry.Target>();
    final Function1<MapEntry, Boolean> _function = (MapEntry it) -> {
      return Boolean.valueOf(this.filterMe(it));
    };
    Iterable<MapEntry> _iterable = IteratorExtensions.<MapEntry>toIterable(IteratorExtensions.<MapEntry>filter(Iterators.<MapEntry>filter(this.targetModel.getAllContents(), MapEntry.class), _function));
    for (final MapEntry me : _iterable) {
      Volume2MapEntry.Target _target = new Volume2MapEntry.Target(me);
      _matches.add(_target);
    }
    for (final Volume2MapEntry.Target _match : _matches) {
      {
        final MapEntry me_1 = _match.me;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(me_1));
        final Elem2Elem.CorrElemType _vType = new Elem2Elem.CorrElemType("Volume", false);
        final List<CorrElem> _src = this.getOrCreateSrc(_corr, _vType);
        CorrElem _get = _src.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Volume v = ((Volume) _unwrap);
        v.setName(me_1.getKey());
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterMe(final MapEntry me);

  protected static Volume2MapEntry.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Volume2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Volume v = ((Volume) _unwrap);
    return new Volume2MapEntry.Source(v);
  }

  protected static Volume2MapEntry.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Volume2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final MapEntry me = ((MapEntry) _unwrap);
    return new Volume2MapEntry.Target(me);
  }
}
