package clojure4j.core;



public final class PersistentSortedMap<K, V> extends AbstractPersistentMap<K, V> {
    
    public PersistentSortedMap() {
        super(Bridge.sortedMap.invoke());
    }
    
    public PersistentSortedMap(BinaryFn<K, K, Boolean> comparator) {
        super(Bridge.sortedMapBy.invoke(comparator));
    }
    
    public PersistentSortedMap(K key, V val) {
        super(Bridge.sortedMap.invoke(key, val));
    }
    
    public PersistentSortedMap(IMapEntry<K,V> entry) {
        this(entry.key(), entry.val());
    }

    @SafeVarargs
    public PersistentSortedMap(IMapEntry<K,V>... entries) {
        super(buildFromVarArgs(Bridge.sortedMap.invoke(), entries));
    }
    
    // REVIEW This constructor should probably be package visibility for everything
    PersistentSortedMap(Object internal) {
        super(internal);
    }

    @Override
    public IPersistentMap<K, V> wrap(Object internal) {
        return new PersistentSortedMap<K, V>(internal);
    }

}