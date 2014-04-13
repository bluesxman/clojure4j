package clojure4j.core;



public final class PersistentVector<T> 
    extends AbstractPersistentCollection<T>
    implements IPersistentVector<T> {
    
    public PersistentVector(Object internal) {
        super(internal);
    }

    public PersistentVector() {
        super(Bridge.vector.invoke());
    }
        
    @SafeVarargs
    public PersistentVector(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }
    
    @SuppressWarnings("unchecked")
    public T get(Long key) {
        return (T) Bridge.get.invoke(internal, key);
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
    public boolean containsIndex(long idx) {
        return (boolean) Bridge.contains.invoke(getInternal(), idx);
    }
    

}