package clojure4j.core;

import java.util.function.Function;

public interface UnaryFn <T, R> extends Function<T, R>, TypedFn<R> {

    @SuppressWarnings("unchecked")
    @Override
    default Object invoke(Object arg1) {
        //TODO Do this for other Fns
        return apply(arg1 instanceof clojure.lang.MapEntry ? (T) new MapEntry<>(arg1) : (T) arg1);
    }
}
