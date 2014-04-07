package clojure4j.core;


public interface IPersistentMap<K, V> extends Associative<K, V>, IPersistentCollection<IMapEntry<K, V>> {
    public <KK extends K, VV extends V> IPersistentMap<K, V> assoc(KK key, VV value);
    public <KK extends K> IPersistentMap<K, V> dissoc(KK key);
    @Override
    public IPersistentMap<K, V> cons(IMapEntry<K, V> entry);
}
