package clojure4j.core;

public final class PersistentTreeMap<K, V> extends AbstractAssociative<K, V> {
    public PersistentTreeMap() {
        super((clojure.lang.Associative ) Bridge.sortedMap.invoke());
    }
}