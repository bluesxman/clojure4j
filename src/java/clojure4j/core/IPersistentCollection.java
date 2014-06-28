package clojure4j.core;

import java.util.Comparator;

// REVIEW support multiple args for fns like apply and map? E.g.:
// public <R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T>... colls);
public interface IPersistentCollection<T> extends Internal, Seqable<T> {
    
    public <R> R apply(ApplySeqFn<T, R> fn);
    
    public ISeq<T> filter(UnaryFn<T, Boolean> pred);
    
    public <R> ISeq<R> map(UnaryFn<T, R> fn);
    
    public <R extends T> R reduce(BinaryFn<T, T, R> fn);
    public <U> U reduce(BinaryFn<U, T, U> fn, U initial);
    
    public IPersistentCollection<T> conj(T value);
        
    public ISeq<T> cons(T value);
    
    public IPersistentCollection<T> into(IPersistentCollection<T> from);
    
    public int count();
    
    public boolean isEmpty();
    public boolean isSome(UnaryFn<T,Boolean> fn);
    
    public T first();
    public T second();
    
    public ISeq<T> rest();
    
    public ISeq<T> cycle();
    
    public ISeq<T> take(int n);
    public ISeq<T> takeWhile(UnaryFn<? super T, Boolean> pred);

    public ISeq<T> drop(int n);
    public ISeq<T> dropWhile(UnaryFn<? super T, Boolean> pred);
    
    public ISeq<T> distinct();
    
    public ISeq<T> sort(Comparator<T> comp);
    
    public ISeq<T> concat(IPersistentCollection<T> col);
    @SuppressWarnings("unchecked")
	public ISeq<T> concat(IPersistentCollection<T>... col);
//    public <U> ISeq<U> flatten();
    public <R> ISeq<R> mapcat(UnaryFn<T, IPersistentCollection<R>> fn);
    
    public ISeq<T> reverse();
    
//    public IPersistentCollection<T> wrap(Object internal);
}
