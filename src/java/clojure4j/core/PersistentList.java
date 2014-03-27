package clojure4j.core;

import clojure.lang.IPersistentList;

// TODO: decide if want generic typing
public class PersistentList<T> {
    private final clojure.lang.IPersistentList internal;
    
    @SafeVarargs
    public PersistentList(T... elements) {
        internal = (IPersistentList) Core.list.invoke();
        for(T t : elements) {
            Core.conj.invoke(internal, t);
        }
    }
    
    public IPersistentList map(Object fn) {
        return (IPersistentList) Core.map.apply(fn, internal);
    }
    
    @SuppressWarnings("unchecked")
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Core.apply.invoke(fn, internal);
    }
}
