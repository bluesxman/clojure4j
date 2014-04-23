package clojure4j.core;

public interface IPersistentVector<T> extends IPersistentStack<T>, Associative<Long, T>, Sequential<T> {
    public IPersistentVector<T> conj(T value);
    // REVIEW Probably handling generics badly
    public <KK extends Long, TT extends T> IPersistentVector<T> assoc(KK key, TT value);
    public IPersistentVector<T> dissoc(Long key);

    @Override
    public IPersistentVector<T> pop();
    
    public IPersistentVector<T> subvec(int start);
    public IPersistentVector<T> subvec(int start, int end);
    public IPersistentVector<T> replace(IPersistentVector<Integer> keys);
}
