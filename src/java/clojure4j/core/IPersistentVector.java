package clojure4j.core;

public interface IPersistentVector<T> extends IPersistentStack<T>, Associative<Long, T>, Sequential {
    public IPersistentVector<T> conj(T value);
    public IPersistentVector<T> cons(T value);
    // REVIEW Probably handling generics badly
    public <KK extends Long, TT extends T> IPersistentVector<T> assoc(KK key, TT value);
    public IPersistentVector<T> dissoc(Long key);

}
