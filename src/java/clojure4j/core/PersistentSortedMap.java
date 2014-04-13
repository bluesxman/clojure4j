package clojure4j.core;



public final class PersistentSortedMap<K, V> extends AbstractPersistentMap<K, V> {
    
    public PersistentSortedMap() {
        super((clojure.lang.Associative ) Bridge.sortedMap.invoke());
    }
    
    @SafeVarargs
    public <KV extends Object> PersistentSortedMap(KV... keyvals) {
        super(buildFromVarArgs(Bridge.sortedMap.invoke(), keyvals));
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