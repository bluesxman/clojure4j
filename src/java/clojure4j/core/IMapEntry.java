package clojure4j.core;

import java.util.Map;

public interface IMapEntry<K, V> extends Map.Entry<K,V> {
    public K key();
    public V val();
}
