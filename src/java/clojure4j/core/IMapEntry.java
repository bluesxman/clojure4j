package clojure4j.core;

import java.util.Map;

// REVIEW Should try to extend vector/collection???
public interface IMapEntry<K, V> extends Map.Entry<K,V>, Internal {
    public K key();
    public V val();
    
    public K first();
    public V second();
}
