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
    
}
