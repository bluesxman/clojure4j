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
}
