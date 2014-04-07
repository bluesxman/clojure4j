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
    
    public IPersistentVector<T> conj(T value) {
        return new PersistentVector<T>(Bridge.conj.invoke(internal, value));
    }
    
    @Override
    public PDSType getType() {
        return PDSType.Vector;
    }

    @Override
    public <KK extends Long, VV extends T> IPersistentVector<T> assoc(KK key, VV value) {
        return new PersistentVector<T>(Bridge.assoc.invoke(getInternal(), key, value));
    }

    @Override
    public IPersistentVector<T> dissoc(Long key) {
        return new PersistentVector<T>(Bridge.dissoc.invoke(getInternal(), key));
    }
    

}