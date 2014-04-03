package clojure4j.core;


public interface IPersistentMap<K, V> extends Associative<K, V>, IPersistentCollection<IMapEntry<K, V>> {
    public IPersistentMap<K, V> assoc(K key, V value);
    public IPersistentMap<K, V> dissoc(K key);
}
