package clojure4j.core;

import java.util.Comparator;


public abstract class AbstractPersistentCollection<T> implements IPersistentCollection<T> {
    protected final Object internal;
    
    AbstractPersistentCollection(Object internal) {
        this.internal = internal;
    }
        
    public AbstractPersistentCollection(clojure.lang.ISeq list) {
        internal = list;
    }
    
    @Override
    public ISeq<T> filter(UnaryFn<T, Boolean> pred) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.filter.invoke(pred, internal));
    }
    
    @Override
    public <R> ISeq<R> map(UnaryFn<T, R> fn) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, internal));
    }
    
    @Override
    public ISeq<T> cons(T value) {
        return new Seq<T>(Bridge.cons.invoke(value, getInternal()));
    }
    
//    @SuppressWarnings("hiding")
//    public <T extends Internal> ISeq<T> cons(T value) {
//        return new Seq<T>(Bridge.cons.invoke(value.getInternal(), getInternal()));        
//    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Bridge.apply.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> R reduce(BinaryFn<T, T, R> fn) {
        return (R) Bridge.reduce.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> R reduce(BinaryFn<T, T, R> fn, T initial) {
        return (R) Bridge.reduce.invoke(fn, initial, getInternal());
    }
    
    @Override
    public int count() {
        return (int) Bridge.count.invoke(getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T first() {
        return (T) Bridge.first.invoke(getInternal());
    }
    
    @Override 
    public ISeq<T> rest() {
        return new Seq<>(Bridge.rest.invoke(getInternal()));
    }

    @Override
    public Object getInternal() {
        return internal;
    }
    
    @Override
    public ISeq<T> seq() {
        return new Seq<T>(Bridge.seq.invoke(getInternal()));
    }
    
    @Override
    public ISeq<T> cycle() {
        return new Seq<T>(Bridge.cycle.invoke(getInternal()));
    }

    @Override
    public ISeq<T> take(int n) {
        return new Seq<T>(Bridge.take.invoke(n, getInternal()));
    }
    
    @Override
    public ISeq<T> takeWhile(UnaryFn<? super T, Boolean> pred) {
        return new Seq<T>(Bridge.takeWhile.invoke(pred, getInternal()));
    }

    @Override
    public ISeq<T> distinct() {
        return new Seq<T>(Bridge.distinct.invoke(getInternal()));
    }

    @Override
    public ISeq<T> sort(Comparator<T> comp) {
        return new Seq<T>(Bridge.sort.invoke(comp, getInternal()));
    }

}
