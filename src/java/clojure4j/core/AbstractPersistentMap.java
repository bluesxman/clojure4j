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
    public IPersistentMap<K, V> conj(IMapEntry<K, V> entry) {
        return assoc(entry.key(), entry.val());
    }

    @Override
    public boolean contains(K key) {
        return (boolean) Bridge.contains.invoke(getInternal(), key);
    }

    @Override
    public IPersistentMap<K, V> selectKeys(ISeq<K> keys) {
        return wrap(Bridge.selectKeys.invoke(getInternal(), keys.getInternal()));
    }
    
    @Override
    public ISeq<K> keys() {
        return new Seq<>(Bridge.keys.invoke(getInternal()));
    }
    
    @Override
    public ISeq<V> vals() {
        return new Seq<>(Bridge.vals.invoke(getInternal()));
    }
    
    @Override
    public IMapEntry<K, V> find(K key) {
        return new MapEntry<>(Bridge.find.invoke(getInternal(), key));
    }
    
    @Override
    public IPersistentMap<K, V> into(IPersistentCollection<IMapEntry<K, V>> from) {
        return wrap(Bridge.into.invoke(getInternal(), from.getInternal()));
    }
}
