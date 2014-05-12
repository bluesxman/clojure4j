package clojure4j.core;

import java.util.Comparator;

// REVIEW support multiple args for fns like apply and map? E.g.:
// public <R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T>... colls);
public interface IPersistentCollection<T> extends Internal, Seqable<T>, IVarArgs<T> {
    
    public <R> R apply(VariadicFn<T, R> fn);
    
    public ISeq<T> filter(UnaryFn<T, Boolean> pred);
    
    public <R> ISeq<R> map(UnaryFn<T, R> fn);
    
    public T reduce(BinaryFn<T, T, T> fn);
    public T reduce(BinaryFn<T, T, T> fn, T initial);
    
    public IPersistentCollection<T> conj(T value);
        
    public ISeq<T> cons(T value);
    
    public IPersistentCollection<T> into(IPersistentCollection<T> from);
    
    public int count();
    
    public boolean isEmpty();
    
    public T first();
    public T second();
    
    public ISeq<T> rest();
    
    public ISeq<T> cycle();
    
    public ISeq<T> take(int n);
    
    public ISeq<T> takeWhile(UnaryFn<? super T, Boolean> pred);

    public ISeq<T> distinct();
    
    public ISeq<T> sort(Comparator<T> comp);
    
//    public IPersistentCollection<T> wrap(Object internal);
}
