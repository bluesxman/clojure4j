package clojure4j.core;

import clojure.lang.ISeq;

public interface VariadicFn<T,R> extends TypedFn<R> {
    @SuppressWarnings("unchecked")
    public R apply(T... args);
    
    //TODO Seems like we hae to do something similar to clojure if we want to target apply with java 8
    //lambdas
    @Override
    default Object applyTo(ISeq arglist) {
        return null;
    }
}
