package clojure4j.core;

public final class PersistentHashMap<K, V> 
    extends AbstractAssociative<K, V> 
    implements IPersistentMap<K, V> {
    
    public PersistentHashMap() {
        super((clojure.lang.Associative ) Bridge.hashMap.invoke());
    }
}