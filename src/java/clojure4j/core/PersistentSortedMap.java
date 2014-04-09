package clojure4j.core;



public final class PersistentSortedMap<K, V> 
    extends AbstractPersistentCollection<IMapEntry<K, V>>
    implements IPersistentMap<K, V> {
    
    public PersistentSortedMap() {
        super((clojure.lang.Associative ) Bridge.sortedMap.invoke());
    }
    
    // REVIEW This constructor should probably be package visibility for everything
    PersistentSortedMap(Object internal) {
        super(internal);
    }
    
    @Override
    public <KK extends K, VV extends V> IPersistentMap<K, V> assoc(KK key, VV value) {
        return new PersistentSortedMap<K, V>(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key) {
        return new PersistentSortedMap<K, V>(Bridge.dissoc.invoke(internal, key));
    }

    @Override
    public IPersistentMap<K, V> conj(IMapEntry<K, V> value) {
        // TODO Auto-generated method stub
        return null;
    }
}