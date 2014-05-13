package clojure4j.core;

import java.util.Comparator;
import java.util.NoSuchElementException;


public abstract class AbstractPersistentCollection<T> 
extends AbstractInternal
implements IPersistentCollection<T> {
    
    public AbstractPersistentCollection(Object internal) {
        super(internal);
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
    public <R> R apply(VariadicFn<T, R> fn) {
        return (R) Bridge.apply.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T reduce(BinaryFn<T, T, T> fn) {
        return (T) Bridge.reduce.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <U> U reduce(BinaryFn<U, T, U> fn, U initial) {
        return (U) Bridge.reduce.invoke(fn, initial, getInternal());
    }
    
    @Override
    public int count() {
        return (int) Bridge.count.invoke(getInternal());
    }
    
    @Override
    public boolean isEmpty() {
        return (boolean) Bridge.isEmpty.invoke(getInternal());
    }
    
    @Override
    public boolean isSome(UnaryFn<T,Boolean> fn) {
        return Bridge.isSome.invoke(fn, getInternal()) != null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T first() {
        return (T) Bridge.first.invoke(getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T second() {
        return (T) Bridge.second.invoke(getInternal());
    }
    
    @Override 
    public ISeq<T> rest() {
        return new Seq<>(Bridge.rest.invoke(getInternal()));
    }

    @Override
    public ISeq<T> seq() {
        return isEmpty() ? null : new Seq<T>(Bridge.seq.invoke(getInternal()));
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
    
    @Override
    public T head() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot call head on empty IVarArgs.");
        }
        else {
            return first();
        }
    }

    @Override
    public IVarArgs<T> tail() {
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot call tail on empty IVarArgs.");
        }
        else {
            return rest();
        }
    }

    @Override
    public boolean hasElements() {
        return count() > 0;
    }
}
