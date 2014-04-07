package clojure4j.core;

public interface IPersistentSet<T> extends IPersistentCollection<T> {
    public IPersistentSet<T> cons(T value);
}
