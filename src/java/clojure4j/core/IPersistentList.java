package clojure4j.core;

public interface IPersistentList<T> extends IPersistentCollection<T>, Sequential {
    public IPersistentList<T> cons(T value);
}
