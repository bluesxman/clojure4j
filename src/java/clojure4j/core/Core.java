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
    
    public static final <K, V> IPersistentMap<K, V> hashMap() {
        return new PersistentHashMap<K, V>();
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
    
    @SuppressWarnings("unchecked")
    public static final <T, R> R apply(BinaryFn<T, T, R> fn, IPersistentCollection<T> col) {
        return (R) Bridge.apply.invoke(fn, col.getInternal());
    }

    @SuppressWarnings("unchecked")
    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E value) {
        Object internal = Bridge.conj.invoke(col.getInternal(), value);
        switch(col.getType()){
        case List:
            return (T) new PersistentList<E>(internal);
        case HashMap:
            throw new IllegalArgumentException("Unsupported type: " + col.getClass());
        case HashSet:
            return (T) new PersistentHashSet<E>(internal);
        case SortedMap:
            throw new IllegalArgumentException("Unsupported type: " + col.getClass());
        case SortedSet:
            return (T) new PersistentSortedSet<E>(internal);
        case Vector:
            return (T) new PersistentVector<E>(internal);
        default:
            throw new IllegalArgumentException("Unsupported type: " + col.getClass());
        }
    }
    
    //TODO: Test types and make sure cast is fine
    //REVIEW: This seems better than the solution used in conj.  avoids the switch
    @SuppressWarnings("unchecked")
    public static final <T extends IPersistentCollection<E>, E> T cons(T col, E value) {
        return (T) col.cons(value);
    }
    
    public static <T> T foo(T blah, T meh){
        return blah;
    }
    
    public static <T, TT extends T> T foofoo(TT blah, TT meh) {
        return meh;
    }
    
//    public static final Number bar() {
//        foofoo(10, "ten");
//        return foo(10, "ten");   // This won't compile
//    }
    
    @SuppressWarnings("unchecked")
    public static final <K, V, T extends Associative<K, V>, KK extends K, VV extends V>
        T assoc(T col, KK key, VV val) {

        Object internal = Bridge.assoc.invoke(col.getInternal(), key, val);
        
        switch(col.getType()){
        case HashMap:
            return (T) new PersistentHashMap<K, V>(internal);
        case SortedMap:
            return (T) new PersistentSortedMap<K, V>(internal);
        case Vector:
            return (T) new PersistentVector<V>(internal);
        default:
            throw new IllegalArgumentException("Unsupported type: " + col.getClass());
        }
    }
}
