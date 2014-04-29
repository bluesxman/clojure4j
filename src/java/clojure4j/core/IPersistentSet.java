package clojure4j.core;


public interface IPersistentSet<T> extends IPersistentCollection<T> {
    public IPersistentSet<T> wrap(Object internal);
    
    @Override
    public IPersistentSet<T> conj(T value);
    @Override
    public IPersistentSet<T> into(IPersistentCollection<T> from);

    public IPersistentSet<T> disj(T value);
    public IPersistentSet<T> disj(@SuppressWarnings("unchecked") T... values);
    public boolean contains(T value);
    
    public IPersistentSet<T> select(UnaryFn<T, Boolean> pred);
    public IPersistentSet<T> union(IPersistentSet<T> set2);
    public IPersistentSet<T> difference(IPersistentSet<T> set2);
    public IPersistentSet<T> intersection(IPersistentSet<T> set2);
    public boolean isSubset(IPersistentSet<T> set2);
    public boolean isSuperset(IPersistentSet<T> set2);
}
