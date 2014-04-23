package clojure4j.core;

public interface IPersistentStack<T> extends IPersistentCollection<T> {
    public IPersistentStack<T> pop();
    @SuppressWarnings("unchecked")
    default T peek() {
        return (T) Bridge.peek.invoke(getInternal());
    }
}
