package clojure4j.core;

import java.util.function.BiFunction;

public interface BinaryFn <T, U, R> extends BiFunction<T, U, R>, TypedFn<R> {

    @SuppressWarnings("unchecked")
    @Override
    default Object invoke(Object arg1, Object arg2) {
        return apply((T) arg1, (U) arg2);
    }
}
