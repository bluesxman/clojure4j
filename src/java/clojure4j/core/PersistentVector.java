package clojure4j.core;

public class PersistentVector<T> 
    extends AbstractSequential<T>
    implements IPersistentVector<T> {
    
    PersistentVector(Object internal) {
        super(internal);
    }

    public PersistentVector() {
        super(Bridge.vector.invoke());
    }

    @SafeVarargs
    public PersistentVector(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }

    public PersistentVector(IPersistentCollection<T> elements) {
        super(Bridge.apply.invoke(Bridge.vector, elements.getInternal()));
    }
    
    @SuppressWarnings("unchecked")
    public T get(Long key) {
        return (T) Bridge.get.invoke(getInternal(), key);
    }
   
    @SuppressWarnings("unchecked")
    public T get(Long key, T notFound) {
        return (T) Bridge.get.invoke(getInternal(), key, notFound);
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public T get(int key) {
        return (T) Bridge.get.invoke(getInternal(), key);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T get(int key, T notFound) {
        return (T) Bridge.get.invoke(getInternal(), key, notFound);
    }
    
    @Override
    public final IPersistentVector<T> conj(final T value) {
        return new PersistentVector<T>(Bridge.conj.invoke(getInternal(), value));
    }
    
    @Override
    public <KK extends Long, VV extends T> IPersistentVector<T> assoc(KK key, VV value) {
        return new PersistentVector<T>(Bridge.assoc.invoke(getInternal(), key, value));
    }

    @Override
    public IPersistentVector<T> dissoc(Long key) {
        return new PersistentVector<T>(Bridge.dissoc.invoke(getInternal(), key));
    }

    @Override
    public IPersistentVector<T> pop() {
        return new PersistentVector<>(Bridge.pop.invoke(getInternal()));
    }

    @Override
    public IPersistentVector<T> subvec(int start) {
        return new PersistentVector<>(Bridge.subvec.invoke(getInternal(), start));
    }

    @Override
    public IPersistentVector<T> subvec(int start, int end) {
        return new PersistentVector<>(Bridge.subvec.invoke(getInternal(), start, end));
    }

    @Override
    public IPersistentVector<T> replace(IPersistentVector<Integer> keys) {
        return new PersistentVector<>(Bridge.replace.invoke(getInternal(), keys.getInternal()));
    }

    @Override
    public ISeq<T> reverse() {
        return new Seq<>(Bridge.reverse.invoke(getInternal()));
    }

    @Override
    public ISeq<T> rseq() {
        return new Seq<>(Bridge.rseq.invoke(getInternal()));
    }

    @Override
    public IPersistentVector<T> into(IPersistentCollection<T> from) {
        return new PersistentVector<>(Bridge.into.invoke(getInternal(), from.getInternal()));
    }
}