package clojure4j.core;

public interface Associative<K, V> {
    public Associative<K, V> assoc(K key, V value);
    public Associative<K, V> dissoc(K key);
    public V get(K key);
}
