package clojure4j.core;


public final class PersistentHashMap<K, V> 
    extends AbstractPersistentCollection<IMapEntry<K, V>>
    implements IPersistentMap<K, V> {
    
    public PersistentHashMap() {
        super((clojure.lang.Associative ) Bridge.hashMap.invoke());
    }
    
    // REVIEW This constructor should probably be package visibility for everything
    PersistentHashMap(Object internal) {
        super(internal);
    }
    
    @Override
    public final <KK extends K, VV extends V> IPersistentMap<K, V> assoc(final KK key, final VV value) {
        return new PersistentHashMap<K, V>(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key) {
        return new PersistentHashMap<K, V>(Bridge.dissoc.invoke(internal, key));
    }

    @Override
    public IPersistentMap<K, V> conj(IMapEntry<K, V> value) {
        // TODO Auto-generated method stub
        return null;
    }
    
}