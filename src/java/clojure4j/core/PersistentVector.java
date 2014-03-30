package clojure4j.core;


public final class PersistentVector<K, V> 
    extends AbstractAssociative<K, V> 
    implements IPersistentMap<K, V> {

    public PersistentVector() {
        super((clojure.lang.Associative ) Bridge.vector.invoke());
    }
}