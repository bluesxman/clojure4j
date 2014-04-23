package clojure4j.core;

public interface ISeq<T> extends IPersistentCollection<T>, Sequential<T> {
    public ISeq<T> cons(T value);
}
