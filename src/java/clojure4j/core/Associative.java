package clojure4j.core;

public interface Associative<K, V> {
    public <KK extends K, VV extends V> Associative<K, V> assoc(KK key, VV value);
    public <KK extends K> Associative<K, V> dissoc(KK key);    
    public V get(K key);
}
