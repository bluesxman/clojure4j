package clojure4j.core;

public class AbstractAssociative<K, V> 
    extends AbstractPersistentCollection<IMapEntry<K, V>>
    implements Associative<K, V> {

    protected AbstractAssociative(clojure.lang.Associative map) {
        super(map);
    }
    
    public Associative<K, V> assoc(K key, V value) {
        return new AbstractAssociative<K, V>((clojure.lang.Associative) Bridge.assoc.invoke(internal, key, value));
    }
    
    public Associative<K, V> dissoc(K key) {
        return new AbstractAssociative<K, V>((clojure.lang.Associative) Bridge.dissoc.invoke(internal, key));       
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        return (V) Bridge.get.invoke(internal, key);
    }
}