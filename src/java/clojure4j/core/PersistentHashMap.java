package clojure4j.core;


public final class PersistentHashMap<K, V> extends AbstractPersistentMap<K, V> {
    
    public PersistentHashMap() {
        super((clojure.lang.Associative ) Bridge.hashMap.invoke());
    }

    @SafeVarargs
    public <KV extends Object> PersistentHashMap(KV... keyvals) {
        super(buildFromVarArgs(Bridge.hashMap.invoke(), keyvals));
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