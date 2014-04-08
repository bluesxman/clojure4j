package clojure4j.core;

public interface IPersistentSet<T> extends IPersistentCollection<T> {
    @Override
    public IPersistentSet<T> conj(T value);
}
