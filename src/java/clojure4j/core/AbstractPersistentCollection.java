package clojure4j.core;


public abstract class AbstractPersistentCollection<T> implements IPersistentCollection<T> {
    protected final Object internal;
    
    protected AbstractPersistentCollection(Object internal) {
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
    
    @SuppressWarnings("unchecked")
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Bridge.apply.invoke(fn, internal);
    }

    @Override
    public IPersistentCollection<T> conj(T value) {
        return Core.conj(this, value);
    }
    
    @Override
    public int count() {
        return (int) Bridge.count.invoke(getInternal());
    }
    
    public Object getInternal() {
        return internal;
    }
}
