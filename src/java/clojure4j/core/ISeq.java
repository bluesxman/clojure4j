package clojure4j.core;

public interface ISeq<T> extends IPersistentCollection<T> {
    public ISeq<T> cons(T value);
    public boolean containsIndex(int idx);
}
