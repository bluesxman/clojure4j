package clojure4j.core;

public final class Set {

    public static final <K, V, T extends IPersistentMap<K, V>> 
    IPersistentSet<T> join(IPersistentSet<T> set1, IPersistentSet<T> set2) {
        return set1.wrap(Bridge.join.invoke(set1, set2));
    }
    
    public static final <T> IPersistentSet<T> select(UnaryFn<T, Boolean> pred, IPersistentSet<T> set) {
        return set.select(pred);
    }
    
    public static final <K, V, T extends IPersistentMap<K, V>> 
    IPersistentSet<T> project(IPersistentSet<T> set, IPersistentVector<K> keys) {
        return set.wrap(Bridge.project.invoke(set, keys));
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
