package clojure4j.core;


public interface TernaryFn <T, U, V, R> extends TypedFn<R> {

    public R apply(T arg1, U arg2, V arg3);
    
    @SuppressWarnings("unchecked")
    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3) {
        return apply((T) arg1, (U) arg2, (V) arg3);
    }
    
    default UnaryFn<V, R> partial(T t, U u) {
        return v -> apply(t, u, v);
    }

    default BinaryFn<U, V, R> partial(T t) {
        return (u, v) -> apply(t, u, v);
    }
}


