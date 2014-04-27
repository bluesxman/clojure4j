package clojure4j.core;

import clojure.lang.ISeq;

public interface SeqFn<T, R> extends TypedFn<R> {
    
    public R apply(Seqable<T> args);
    
    @Override
    default Object applyTo(ISeq arglist) {
        return apply(new Seq<T>(arglist));
    }
}
