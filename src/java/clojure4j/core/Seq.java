package clojure4j.core;

import clojure.lang.ISeq;

public class Seq<T> {
    private final ISeq internal;
    
    public Seq(ISeq clojSeq) {
        internal = clojSeq;
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
