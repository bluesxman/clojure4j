package clojure4j.core;

public interface IPersistentVector<T> extends IPersistentStack<T>, Associative<Long, T>, Sequential {
    public IPersistentVector<T> conj(T value);
    public IPersistentVector<T> assoc(Long key, T value);
    public IPersistentVector<T> dissoc(Long key);

}
