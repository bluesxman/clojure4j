package clojure4j.core;

// REVIEW May want to actually wrap a clojure MapEntry and extend vector similar to clojure
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
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o instanceof MapEntry) {
            return ((MapEntry<K,V>) o).key().equals(key()) && ((MapEntry<K,V>) o).val().equals(val());
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "[" + key() + ", " + val() + "]";
    }
}
