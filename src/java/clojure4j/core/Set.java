package clojure4j.core;

public final class Set {

    // TODO: impl join in java rather than passthrough to clojure
//    public static final <K, V> IPersistentSet<IPersistentMap<K, V>> join(
//            IPersistentSet<IPersistentMap<K, V>> set1, 
//            IPersistentSet<IPersistentMap<K, V>> set2) {
//        return set1.wrap(Bridge.join.invoke(set1.getInternal(), set2.getInternal()));
//    }
    
    public static final <K, V, T extends IPersistentMap<K, V>> 
    IPersistentSet<T> project(IPersistentSet<T> set, IPersistentVector<K> keys) {
        return set.wrap(Bridge.project.invoke(set.getInternal(), keys.getInternal()));
    }
    
    public static final <T> IPersistentSet<T> select(UnaryFn<T, Boolean> pred, IPersistentSet<T> set) {
        return set.select(pred);
    }
    
    public static final <T> IPersistentSet<T> union(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.union(set2);
    }
    public static final <T> IPersistentSet<T> difference(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.difference(set2);
    }
    public static final <T> IPersistentSet<T> intersection(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.intersection(set2);
    }
    public static final <T> boolean isSubset(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.isSubset(set2);
    }
    public static final <T> boolean isSuperset(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.isSuperset(set2);
    }

}
