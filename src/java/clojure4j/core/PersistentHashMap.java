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
    public IPersistentMap<K, V> assoc(K key, V value) {
        // TODO Auto-generated method stub
        return new PersistentHashMap<>(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public IPersistentMap<K, V> dissoc(K key) {
        // TODO Auto-generated method stub
        return new PersistentHashMap<>(Bridge.dissoc.invoke(internal, key));
    }

}