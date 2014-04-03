package clojure4j.core;

public interface Associative<K, V> extends Internal {
    public Associative<K, V> assoc(K key, V value);
    public Associative<K, V> dissoc(K key);
    
    @SuppressWarnings("unchecked")
    default V get(K key) {
        return (V) Bridge.get.invoke(getInternal(), key);
    }

}
