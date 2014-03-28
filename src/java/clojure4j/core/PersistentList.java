package clojure4j.core;

import clojure.lang.IPersistentList;

// TODO: decide if want generic typing
public class PersistentList<T> {
    private final clojure.lang.ISeq internal;
    
    @SafeVarargs
    public PersistentList(T... elements) {
        Object clojList = (IPersistentList) Bridge.list.invoke();
        for(int i = elements.length - 1; i >= 0; i--) {
            clojList = Bridge.conj.invoke(clojList, elements[i]);
        }
        internal = (clojure.lang.ISeq) clojList;
    }
    
    public PersistentList(clojure.lang.ISeq list) {
        internal = list;
    }
    
    public Seq<T> filter(UnaryFn<T, Boolean> pred) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.filter.invoke(pred, internal));
    }
    
    public <R> Seq<R> map(UnaryFn<T, R> fn) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, internal));
    }
    
    @SuppressWarnings("unchecked")
    public <R> R apply(BinaryFn<T, T, R> fn) {
        return (R) Bridge.apply.invoke(fn, internal);
    }
}
