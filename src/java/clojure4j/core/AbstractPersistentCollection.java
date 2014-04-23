package clojure4j.core;


public abstract class AbstractPersistentCollection<T> implements IPersistentCollection<T> {
    protected final Object internal;
    
    AbstractPersistentCollection(Object internal) {
        this.internal = internal;
    }
        
    public AbstractPersistentCollection(clojure.lang.ISeq list) {
        internal = list;
    }
    
    public ISeq<T> filter(UnaryFn<T, Boolean> pred) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.filter.invoke(pred, internal));
    }
    
    public <R> ISeq<R> map(UnaryFn<T, R> fn) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, internal));
    }
    
    public ISeq<T> cons(T value) {
        return new Seq<T>(Bridge.cons.invoke(value, getInternal()));
    }
    
//    @SuppressWarnings("hiding")
//    public <T extends Internal> ISeq<T> cons(T value) {
//        return new Seq<T>(Bridge.cons.invoke(value.getInternal(), getInternal()));        
//    }
    
    @SuppressWarnings("unchecked")
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Bridge.apply.invoke(fn, internal);
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
    public Object getInternal() {
        return internal;
    }
    
    @Override
    public ISeq<T> seq() {
        return new Seq<T>(Bridge.seq.invoke(getInternal()));
    }
}
