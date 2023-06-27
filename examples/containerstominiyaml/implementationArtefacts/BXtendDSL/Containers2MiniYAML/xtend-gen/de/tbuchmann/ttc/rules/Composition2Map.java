package de.tbuchmann.ttc.rules;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import containers.Composition;
import containers.Container;
import containers.Image;
import containers.Node;
import containers.Volume;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import miniyaml.Map;
import miniyaml.MapEntry;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public abstract class Composition2Map extends Elem2Elem {
  @Data
  protected static class Type4entries {
    private final List<MapEntry> entries;

    public Type4entries(final List<MapEntry> entries) {
      super();
      this.entries = entries;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.entries== null) ? 0 : this.entries.hashCode());
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
      Composition2Map.Type4entries other = (Composition2Map.Type4entries) obj;
      if (this.entries == null) {
        if (other.entries != null)
          return false;
      } else if (!this.entries.equals(other.entries))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("entries", this.entries);
      return b.toString();
    }

    @Pure
    public List<MapEntry> getEntries() {
      return this.entries;
    }
  }

  @Data
  protected static class Type4nodes {
    private final List<Node> nodes;

    public Type4nodes(final List<Node> nodes) {
      super();
      this.nodes = nodes;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.nodes== null) ? 0 : this.nodes.hashCode());
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
      Composition2Map.Type4nodes other = (Composition2Map.Type4nodes) obj;
      if (this.nodes == null) {
        if (other.nodes != null)
          return false;
      } else if (!this.nodes.equals(other.nodes))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("nodes", this.nodes);
      return b.toString();
    }

    @Pure
    public List<Node> getNodes() {
      return this.nodes;
    }
  }

  @Data
  protected static class Source {
    private final Composition c;

    public Source(final Composition c) {
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
      Composition2Map.Source other = (Composition2Map.Source) obj;
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
    public Composition getC() {
      return this.c;
    }
  }

  @Data
  protected static class Target {
    private final Map m;

    public Target(final Map m) {
      super();
      this.m = m;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.m== null) ? 0 : this.m.hashCode());
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
      Composition2Map.Target other = (Composition2Map.Target) obj;
      if (this.m == null) {
        if (other.m != null)
          return false;
      } else if (!this.m.equals(other.m))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("m", this.m);
      return b.toString();
    }

    @Pure
    public Map getM() {
      return this.m;
    }
  }

  public Composition2Map(final Containers2MiniYAML trafo) {
    super("Composition2Map", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Composition2Map.Source> _matches = new ArrayList<Composition2Map.Source>();
    Iterable<Composition> _iterable = IteratorExtensions.<Composition>toIterable(Iterators.<Composition>filter(this.sourceModel.getAllContents(), Composition.class));
    for (final Composition c : _iterable) {
      Composition2Map.Source _source = new Composition2Map.Source(c);
      _matches.add(_source);
    }
    for (final Composition2Map.Source _match : _matches) {
      {
        final Composition c_1 = _match.c;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(c_1));
        final Elem2Elem.CorrElemType _mType = new Elem2Elem.CorrElemType("Map", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _mType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Map m = ((Map) _unwrap);
        final Consumer<Node> _function = (Node it) -> {
          Elem2Elem.assertRuleId(this.getCorr(it), "Image2MapEntry", "Container2MapEntry", "Volume2MapEntry");
        };
        c_1.getNodes().forEach(_function);
        final Function1<Node, Boolean> _function_1 = (Node it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Image2MapEntry"));
        };
        final Function1<Node, MapEntry> _function_2 = (Node it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((MapEntry) _unwrap_1);
        };
        final List<MapEntry> _nodImaMe = IterableExtensions.<MapEntry>toList(IterableExtensions.<Node, MapEntry>map(IterableExtensions.<Node>filter(c_1.getNodes(), _function_1), _function_2));
        final Function1<Node, Boolean> _function_3 = (Node it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Container2MapEntry"));
        };
        final Function1<Node, MapEntry> _function_4 = (Node it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((MapEntry) _unwrap_1);
        };
        final List<MapEntry> _nodConMe = IterableExtensions.<MapEntry>toList(IterableExtensions.<Node, MapEntry>map(IterableExtensions.<Node>filter(c_1.getNodes(), _function_3), _function_4));
        final Function1<Node, Boolean> _function_5 = (Node it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Volume2MapEntry"));
        };
        final Function1<Node, MapEntry> _function_6 = (Node it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((MapEntry) _unwrap_1);
        };
        final List<MapEntry> _nodVolMe = IterableExtensions.<MapEntry>toList(IterableExtensions.<Node, MapEntry>map(IterableExtensions.<Node>filter(c_1.getNodes(), _function_5), _function_6));
        final Composition2Map.Type4entries _entries = this.entriesFrom(_nodImaMe, _nodConMe, _nodVolMe);
        m.getEntries().clear();
        m.getEntries().addAll(_entries.entries);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public void onTrgElemCreation(final EObject trgElem) {
    int _corrElemPosition = this.getCorrElemPosition(trgElem);
    switch (_corrElemPosition) {
      case 0:
        this.onMCreation(((Map) trgElem));
        break;
    }
  }

  protected abstract void onMCreation(final Map m);

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Composition2Map.Target> _matches = new ArrayList<Composition2Map.Target>();
    final Function1<Map, Boolean> _function = (Map it) -> {
      return Boolean.valueOf(this.filterM(it));
    };
    Iterable<Map> _iterable = IteratorExtensions.<Map>toIterable(IteratorExtensions.<Map>filter(Iterators.<Map>filter(this.targetModel.getAllContents(), Map.class), _function));
    for (final Map m : _iterable) {
      Composition2Map.Target _target = new Composition2Map.Target(m);
      _matches.add(_target);
    }
    for (final Composition2Map.Target _match : _matches) {
      {
        final Map m_1 = _match.m;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(m_1));
        final Elem2Elem.CorrElemType _cType = new Elem2Elem.CorrElemType("Composition", false);
        final List<CorrElem> _src = this.getOrCreateSrc(_corr, _cType);
        CorrElem _get = _src.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Composition c = ((Composition) _unwrap);
        final Function1<MapEntry, Boolean> _function_1 = (MapEntry me) -> {
          return Boolean.valueOf((((!Objects.equal(me.getKey(), "version")) && (!Objects.equal(me.getKey(), "volumes"))) && (!Objects.equal(me.getKey(), "services"))));
        };
        final Function1<MapEntry, Boolean> _function_2 = (MapEntry it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Image2MapEntry"));
        };
        final Function1<MapEntry, Image> _function_3 = (MapEntry it) -> {
          CorrElem _get_1 = this.getCorr(it).getSource().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Image) _unwrap_1);
        };
        final List<Image> _entImaImg = IterableExtensions.<Image>toList(IterableExtensions.<MapEntry, Image>map(IterableExtensions.<MapEntry>filter(IterableExtensions.<MapEntry>filter(Iterables.<MapEntry>filter(m_1.getEntries(), MapEntry.class), _function_1), _function_2), _function_3));
        final Function1<MapEntry, Boolean> _function_4 = (MapEntry me) -> {
          return Boolean.valueOf((((!Objects.equal(me.getKey(), "version")) && (!Objects.equal(me.getKey(), "volumes"))) && (!Objects.equal(me.getKey(), "services"))));
        };
        final Function1<MapEntry, Boolean> _function_5 = (MapEntry it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Container2MapEntry"));
        };
        final Function1<MapEntry, Container> _function_6 = (MapEntry it) -> {
          CorrElem _get_1 = this.getCorr(it).getSource().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Container) _unwrap_1);
        };
        final List<Container> _entConC = IterableExtensions.<Container>toList(IterableExtensions.<MapEntry, Container>map(IterableExtensions.<MapEntry>filter(IterableExtensions.<MapEntry>filter(Iterables.<MapEntry>filter(m_1.getEntries(), MapEntry.class), _function_4), _function_5), _function_6));
        final Function1<MapEntry, Boolean> _function_7 = (MapEntry me) -> {
          return Boolean.valueOf((((!Objects.equal(me.getKey(), "version")) && (!Objects.equal(me.getKey(), "volumes"))) && (!Objects.equal(me.getKey(), "services"))));
        };
        final Function1<MapEntry, Boolean> _function_8 = (MapEntry it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "Volume2MapEntry"));
        };
        final Function1<MapEntry, Volume> _function_9 = (MapEntry it) -> {
          CorrElem _get_1 = this.getCorr(it).getSource().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Volume) _unwrap_1);
        };
        final List<Volume> _entVolV = IterableExtensions.<Volume>toList(IterableExtensions.<MapEntry, Volume>map(IterableExtensions.<MapEntry>filter(IterableExtensions.<MapEntry>filter(Iterables.<MapEntry>filter(m_1.getEntries(), MapEntry.class), _function_7), _function_8), _function_9));
        final Composition2Map.Type4nodes _nodes = this.nodesFrom(m_1.getEntries(), _entImaImg, _entConC, _entVolV);
        c.getNodes().clear();
        c.getNodes().addAll(_nodes.nodes);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterM(final Map m);

  protected abstract Composition2Map.Type4entries entriesFrom(final List<MapEntry> nodImaMe, final List<MapEntry> nodConMe, final List<MapEntry> nodVolMe);

  protected abstract Composition2Map.Type4nodes nodesFrom(final List<MapEntry> entries, final List<Image> entImaImg, final List<Container> entConC, final List<Volume> entVolV);

  protected static Composition2Map.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Composition2Map");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Composition c = ((Composition) _unwrap);
    return new Composition2Map.Source(c);
  }

  protected static Composition2Map.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Composition2Map");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Map m = ((Map) _unwrap);
    return new Composition2Map.Target(m);
  }
}
