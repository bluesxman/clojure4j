package clojure4j.core;

public interface Sequential<T> {
    public T nth(int idx);
    public T nth(int idx, T notFound);
    public T nth(long idx);
    public T nth(long idx, T notFound);
    public boolean containsIndex(long idx);
}
