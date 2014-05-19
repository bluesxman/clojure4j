package clojure4j.core;

public class MapEntry<K, V> extends AbstractInternal implements IMapEntry<K, V> {
    
    public MapEntry(K key, V val) {
        super(Bridge.vector.invoke(key, val));
    }
    
    MapEntry(Object internal) {
        super(internal);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final K key() {
        return (K) Bridge.get.invoke(getInternal(), 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final V val() {
        return (V) Bridge.get.invoke(getInternal(), 1);
    }

    @Override
    public K getKey() {
        return key();
    }

    @Override
    public final V getValue() {
        return val();
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException("MapEntry is immutable.");
    }
        
//    @Override
//    public final K first() {
//        return key();
//    }
//
//    @Override
//    public final V second() {
//        return val();
//    }
}
