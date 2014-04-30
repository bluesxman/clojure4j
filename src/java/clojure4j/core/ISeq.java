package clojure4j.core;

public interface ISeq<T> extends IPersistentCollection<T>, Sequential<T> {
    @Override
    public ISeq<T> conj(T value);
    public ISeq<T> cons(T value);
}
