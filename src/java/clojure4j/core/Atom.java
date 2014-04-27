package clojure4j.core;

public class Atom<T> extends AbstractInternal {
    
    public Atom(T initialValue) {
        super(Bridge.atom.invoke(initialValue));
    }
    
    @SuppressWarnings("unchecked")
    public T deref() {
        return (T) Bridge.deref.invoke(internal);
    }
    
    @SuppressWarnings("unchecked")
    public T reset(T newVal) {
        return (T) Bridge.reset.invoke(internal, newVal);
    }

    @SuppressWarnings("unchecked")
    public T swap(UnaryFn<T,T> fn) {
        return (T) Bridge.swap.invoke(internal, fn);
    }

    @SuppressWarnings("unchecked")
    public <X> T swap(BinaryFn<T,X,T> fn, X x) {
        return (T) Bridge.swap.invoke(internal, fn, x);
    }

    @SuppressWarnings("unchecked")
    public <X, Y> T swap(TernaryFn<T,X,Y,T> fn, X x, Y y) {
        return (T) Bridge.swap.invoke(internal, fn, x, y);
    }    

}
