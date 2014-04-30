package clojure4j.core;

import clojure.lang.ISeq;

public interface ApplySeqFn<T, R> extends TypedFn<R> {
    
    public R apply(ApplySeq<T> args);
    
    @Override
    default Object applyTo(ISeq arglist) {
        return apply(new ApplySeq<T>(arglist));
    }
}
