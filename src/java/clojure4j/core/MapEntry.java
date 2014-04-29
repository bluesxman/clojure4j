package clojure4j.core;

public class MapEntry<K, V> implements IMapEntry<K, V> {
    private K key;
    private V val;
    
    public MapEntry(K key, V val) {
        this.key = key;
        this.val = val;
    }
    
    @SuppressWarnings("unchecked")
    MapEntry(Object internal) {
        clojure.lang.IMapEntry entry = (clojure.lang.IMapEntry) internal;
        key = (K) entry.key();
        val = (V) entry.val();
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V val() {
        return val;
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
}
