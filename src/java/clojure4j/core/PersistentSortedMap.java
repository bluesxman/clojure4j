package clojure4j.core;


public final class PersistentSortedMap<K, V> 
    extends AbstractPersistentCollection<IMapEntry<K, V>>
    implements IPersistentMap<K, V> {
    
    public PersistentSortedMap() {
        super((clojure.lang.Associative ) Bridge.sortedMap.invoke());
    }
    
    public PersistentSortedMap(Object internal) {
        super(internal);
    }
    
    @Override
    public PDSType getType() {
        return PDSType.SortedMap;
    }
    
    @Override
    public IPersistentMap<K, V> assoc(K key, V value) {
        // TODO Auto-generated method stub
        return new PersistentSortedMap<K, V>(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public IPersistentMap<K, V> dissoc(K key) {
        // TODO Auto-generated method stub
        return new PersistentSortedMap<K, V>(Bridge.dissoc.invoke(internal, key));
    }


}