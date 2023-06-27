package de.tbuchmann.ttc.rules;

import com.google.common.collect.Iterators;
import containers.Image;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Containers2MiniYAML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import miniyaml.MapEntry;
import miniyaml.Value;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public abstract class Image2MapEntry extends Elem2Elem {
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
      Image2MapEntry.Type4value other = (Image2MapEntry.Type4value) obj;
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
  protected static class Type4image {
    private final String image;

    public Type4image(final String image) {
      super();
      this.image = image;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.image== null) ? 0 : this.image.hashCode());
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
      Image2MapEntry.Type4image other = (Image2MapEntry.Type4image) obj;
      if (this.image == null) {
        if (other.image != null)
          return false;
      } else if (!this.image.equals(other.image))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("image", this.image);
      return b.toString();
    }

    @Pure
    public String getImage() {
      return this.image;
    }
  }

  @Data
  protected static class Source {
    private final Image img;

    public Source(final Image img) {
      super();
      this.img = img;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.img== null) ? 0 : this.img.hashCode());
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
      Image2MapEntry.Source other = (Image2MapEntry.Source) obj;
      if (this.img == null) {
        if (other.img != null)
          return false;
      } else if (!this.img.equals(other.img))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("img", this.img);
      return b.toString();
    }

    @Pure
    public Image getImg() {
      return this.img;
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
      Image2MapEntry.Target other = (Image2MapEntry.Target) obj;
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

  public Image2MapEntry(final Containers2MiniYAML trafo) {
    super("Image2MapEntry", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Image2MapEntry.Source> _matches = new ArrayList<Image2MapEntry.Source>();
    Iterable<Image> _iterable = IteratorExtensions.<Image>toIterable(Iterators.<Image>filter(this.sourceModel.getAllContents(), Image.class));
    for (final Image img : _iterable) {
      Image2MapEntry.Source _source = new Image2MapEntry.Source(img);
      _matches.add(_source);
    }
    for (final Image2MapEntry.Source _match : _matches) {
      {
        final Image img_1 = _match.img;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(img_1));
        final Elem2Elem.CorrElemType _meType = new Elem2Elem.CorrElemType("MapEntry", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _meType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final MapEntry me = ((MapEntry) _unwrap);
        final Image2MapEntry.Type4value _value = this.valueFrom(img_1.getImage());
        me.setValue(_value.value);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public void onTrgElemCreation(final EObject trgElem) {
    int _corrElemPosition = this.getCorrElemPosition(trgElem);
    switch (_corrElemPosition) {
      case 0:
        this.onMeCreation(((MapEntry) trgElem));
        break;
    }
  }

  protected abstract void onMeCreation(final MapEntry me);

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Image2MapEntry.Target> _matches = new ArrayList<Image2MapEntry.Target>();
    final Function1<MapEntry, Boolean> _function = (MapEntry it) -> {
      return Boolean.valueOf(this.filterMe(it));
    };
    Iterable<MapEntry> _iterable = IteratorExtensions.<MapEntry>toIterable(IteratorExtensions.<MapEntry>filter(Iterators.<MapEntry>filter(this.targetModel.getAllContents(), MapEntry.class), _function));
    for (final MapEntry me : _iterable) {
      Image2MapEntry.Target _target = new Image2MapEntry.Target(me);
      _matches.add(_target);
    }
    for (final Image2MapEntry.Target _match : _matches) {
      {
        final MapEntry me_1 = _match.me;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(me_1));
        final Elem2Elem.CorrElemType _imgType = new Elem2Elem.CorrElemType("Image", false);
        final List<CorrElem> _src = this.getOrCreateSrc(_corr, _imgType);
        CorrElem _get = _src.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Image img = ((Image) _unwrap);
        final Image2MapEntry.Type4image _image = this.imageFrom(me_1.getValue());
        img.setImage(_image.image);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterMe(final MapEntry me);

  protected abstract Image2MapEntry.Type4value valueFrom(final String image);

  protected abstract Image2MapEntry.Type4image imageFrom(final Value value);

  protected static Image2MapEntry.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Image2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Image img = ((Image) _unwrap);
    return new Image2MapEntry.Source(img);
  }

  protected static Image2MapEntry.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Image2MapEntry");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final MapEntry me = ((MapEntry) _unwrap);
    return new Image2MapEntry.Target(me);
  }
}
