package clojure4j.core;


public interface IPersistentMap<K, V> extends Associative<K, V>, IPersistentCollection<IMapEntry<K, V>> {
    public <KK extends K, VV extends V> IPersistentMap<K, V> assoc(final KK key, final VV value);
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key);
    @Override
    public IPersistentMap<K,V> conj(IMapEntry<K, V> value);
    public boolean contains(K key);
    public IPersistentMap<K, V> wrap(Object internal);
}
