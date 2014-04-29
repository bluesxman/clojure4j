package clojure4j.core;

public interface NaryFn<T,R> extends TypedFn<R> {
    @SuppressWarnings("unchecked")
    public R apply(T... args);
}
