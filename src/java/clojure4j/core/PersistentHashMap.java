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
    public PDSType getType() {
        return PDSType.HashMap;
    }

    @Override
    public <KK extends K, VV extends V> IPersistentMap<K, V> assoc(KK key, VV value) {
        // TODO Auto-generated method stub
        return new PersistentHashMap<K, V>(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key) {
        // TODO Auto-generated method stub
        return new PersistentHashMap<K, V>(Bridge.dissoc.invoke(internal, key));
    }
    
    @Override 
    public IPersistentMap<K, V> cons(IMapEntry<K, V> entry) {
        //TODO: Do entry right.  Need to make IMapEntry impl Internal
        return new PersistentHashMap<K, V>(Bridge.cons.invoke(entry, getInternal()));
    }

}