package clojure4j.core;

public interface Sequential<T> {
    public T nth(int idx);
    public T nth(int idx, T notFound);
    public boolean containsIndex(long idx);
}
