package clojure4j.core;

/**
 * The internal sequence will contain Clojure map entries which must be wrapped before returning them.
 * 
 * @param <K>
 * @param <V>
 */
class EntrySeq<K,V> extends Seq<IMapEntry<K,V>> {

    EntrySeq(Object internal) {
        super(internal);
    }

    @Override
    protected ISeq<IMapEntry<K, V>> wrapSeq(Object internal) {
        return new EntrySeq<>(internal);
    }

    @Override
    public IMapEntry<K, V> first() {
        return new MapEntry<>(super.first());
    }

    @Override
    public IMapEntry<K, V> second() {
        return new MapEntry<>(super.second());
    }
    
    @Override
    public IMapEntry<K, V> nth(int idx) {
        return new MapEntry<>(super.nth(idx));
    }    

    @Override
    public IMapEntry<K, V> nth(int idx, IMapEntry<K, V> notFound) {
        return new MapEntry<>(super.nth(idx, notFound));
    }    
    
    @Override
    public IMapEntry<K, V> nth(long idx) {
        return new MapEntry<>(super.nth(idx));
    }    
    
    @Override
    public IMapEntry<K, V> nth(long idx, IMapEntry<K, V> notFound) {
        return new MapEntry<>(super.nth(idx, notFound));
    }    
}
