package clojure4j.core;

import clojure.lang.IPersistentList;

// TODO: decide if want generic typing
public class PersistentList<T> {
    private final clojure.lang.IPersistentList internal;
    
    @SafeVarargs
    public PersistentList(T... elements) {
        internal = (IPersistentList) Bridge.list.invoke();
        for(T t : elements) {
            Bridge.conj.invoke(internal, t);
        }
    }
    
    public IPersistentList map(Object fn) {
        return (IPersistentList) Bridge.map.apply(fn, internal);
    }
    
    @SuppressWarnings("unchecked")
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Bridge.apply.invoke(fn, internal);
    }
}
