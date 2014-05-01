package clojure4j.core;


public final class PersistentHashMap<K, V> extends AbstractPersistentMap<K, V> {
    
    public PersistentHashMap() {
        super(Bridge.hashMap.invoke());
    }
    
    public PersistentHashMap(K key, V val) {
        super(Bridge.hashMap.invoke(key, val));
    }
    
    public PersistentHashMap(IMapEntry<K,V> entry) {
        this(entry.key(), entry.val());
    }

    @SafeVarargs
    public PersistentHashMap(IMapEntry<K,V>... entries) {
        super(buildFromVarArgs(Bridge.hashMap.invoke(), entries));
    }
    
    // REVIEW This constructor should probably be package visibility for everything
    PersistentHashMap(Object internal) {
        super(internal);
    }
    
    @Override
    public IPersistentMap<K, V> wrap(Object internal) {
        return new PersistentHashMap<K, V>(internal);
    }
    
}