package clojure4j.core;

public interface IPersistentList<T> extends IPersistentStack<T>, Sequential<T> {
    public IPersistentList<T> conj(T value);
    @Override
    public IPersistentList<T> pop();
    @Override
    public IPersistentList<T> into(IPersistentCollection<T> from);
}
