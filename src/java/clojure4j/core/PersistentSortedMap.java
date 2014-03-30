package clojure4j.core;

public final class PersistentSortedMap<K, V> extends AbstractAssociative<K, V> {
    public PersistentSortedMap() {
        super((clojure.lang.Associative ) Bridge.sortedMap.invoke());
    }
}