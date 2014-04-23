package clojure4j.core;

public interface Sequential<T> {
    public T nth(int idx);
    public Object nth(int idx, Object notFound);
    public boolean containsIndex(long idx);
}
