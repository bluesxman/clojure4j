package clojure4j.core;

// REVIEW support multiple args for fns like apply and map? E.g.:
// public <R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T>... colls);
public interface IPersistentCollection<T> extends Internal {
    
    public <R> R apply(BinaryFn<T, T, R> fn);
    
    public ISeq<T> filter(UnaryFn<T, Boolean> pred);
    
    public <R> ISeq<R> map(UnaryFn<T, R> fn);
    
    public IPersistentCollection<T> conj(T value);
    
//    @SuppressWarnings("unchecked")
//    public IPersistentCollection<T> conj(T... values);
    
    public ISeq<T> cons(T value);
    
    public int count();
    
    public T first();
    
    public ISeq<T> rest();
    
    public ISeq<T> seq();
    
//    public IPersistentCollection<T> wrap(Object internal);
}
