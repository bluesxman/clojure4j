package clojure4j.core;

import java.util.function.Function;

public interface UnaryFn <T, R> extends Function<T, R>, TypedFn<R> {

    @SuppressWarnings("unchecked")
    @Override
    default Object invoke(Object arg1) {
        return apply((T) arg1);
    }
}
