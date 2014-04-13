package clojure4j.core;

public abstract class AbstractPersistentMap<K, V> 
extends AbstractPersistentCollection<IMapEntry<K, V>> implements IPersistentMap<K, V>  {

    protected AbstractPersistentMap(Object internal) {
        super(internal);
    }
    
    @SafeVarargs
    protected final static <KV extends Object> Object buildFromVarArgs(Object m, KV... keyvals) {
        for(int i = 0; i < keyvals.length; i += 2) {
            m = Bridge.assoc.invoke(m, keyvals[i], keyvals[i + 1]);
        }
        
        return m;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        return (V) Bridge.get.invoke(getInternal(), key);
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public IPersistentCollection<IMapEntry<K, V>> conj(IMapEntry<K, V>... values) {
//        // TODO Auto-generated method stub
//        return null;
//    }
    
    @Override
    public final <KK extends K, VV extends V> IPersistentMap<K, V> assoc(final KK key, final VV value) {
        return wrap(Bridge.assoc.invoke(internal, key, value));
    }

    @Override
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key) {
        return wrap(Bridge.dissoc.invoke(internal, key));
    }

    @Override
    public IPersistentMap<K, V> conj(IMapEntry<K, V> value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(K key) {
        return (boolean) Bridge.contains.invoke(getInternal(), key);
    }


}
