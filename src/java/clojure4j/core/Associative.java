package clojure4j.core;

public interface Associative<K, V> extends Internal {
    public <KK extends K, VV extends V> Associative<K, V> assoc(KK key, VV value);
    public <KK extends K> Associative<K, V> dissoc(KK key);
    
    @SuppressWarnings("unchecked")
    default V get(K key) {
        return (V) Bridge.get.invoke(getInternal(), key);
    }

}
