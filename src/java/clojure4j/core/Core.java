package clojure4j.core;


public final class Core {
    
    // REVIEW edge cases for varargs not considered?
    @SafeVarargs
    public static final <T> IPersistentList<T> list(T... elements){
        return new PersistentList<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentVector<T> vector(T... elements){
        return new PersistentVector<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> hashSet(T... elements){
        return new PersistentHashSet<T>(elements);
    }

    @SafeVarargs
    public static final <T> IPersistentSet<T> sortedSet(T... elements){
        return new PersistentSortedSet<T>(elements);
    }
    
    public static final <K, V> IPersistentMap<K, V> hashMap(K key, V val) {
        return new PersistentHashMap<K, V>(key, val);
    }

    public static final <K, V> IPersistentMap<K, V> sortedMap(K key, V val) {
        return new PersistentSortedMap<K, V>(key, val);
    }

    public static final <T, R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T> col) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, col.getInternal()));
    }
    
    public static final <T> ISeq<T> filter(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.filter.invoke(pred, col.getInternal()));
    }
    
    public static final <T> ISeq<T> remove(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.remove.invoke(pred, col.getInternal()));
    }
    
    public static final <T, R> R apply(BinaryFn<T, T, R> fn, IPersistentCollection<T> col) {
        return col.apply(fn);
    }

    @SuppressWarnings("unchecked")
    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E value) {
        return (T) col.conj(value);
    }
    
//    @SuppressWarnings("unchecked")
//    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E... values) {
//        return (T) col.conj(values);
//    }
//    
    public static final <T extends IPersistentCollection<E>, E> ISeq<E> cons(E value, T col) {
        return col.cons(value);
    }
    
    public static final <T> IPersistentSet<T> disj(IPersistentSet<T> set, T key) {
        return set.disj(key);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> disj(IPersistentSet<T> set, T... keys) {
        return set.disj(keys);
    }
    
    public static final <T> boolean contains(IPersistentSet<T> set, T value) {
        return set.contains(value);
    }
    
    // TODO Figure out best policy on generics
    @SuppressWarnings("unchecked")
    public static final <K, V, T extends Associative<K, V>, KK extends K, VV extends V>
    T assoc(T col, KK key, VV val) {
        return (T) col.assoc(key, val);
    }
    
    public static final <T> int count(IPersistentCollection<T> col) {
        return col.count();
    }
    
    public static final boolean isOdd(int n) {
        return n % 2 == 0; // TODO use faster method than modulo
    }
    
    public static final boolean isOdd(long n) {
        return n % 2 == 0;
    }
    
    public static final boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static final boolean isEven(long n) {
        return n % 2 == 0;
    }
}
