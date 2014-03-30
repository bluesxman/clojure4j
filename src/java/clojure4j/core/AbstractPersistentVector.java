package clojure4j.core;

public abstract class AbstractPersistentVector<T> 
    extends AbstractPersistentCollection<T>
    implements Associative<Long, T>, IPersistentVector<T> {

    protected AbstractPersistentVector(Object map) {
        super(map);
    }

    public IPersistentVector<T> assoc(Long key, T value) {
        return new PersistentVector<T>((clojure.lang.Associative) Bridge.assoc.invoke(internal, key, value));
    }

    public PersistentVector<T> dissoc(Long key) {
        return new PersistentVector<T>((clojure.lang.Associative) Bridge.dissoc.invoke(internal, key));
    }

    @SuppressWarnings("unchecked")
    public T get(Long key) {
        return (T) Bridge.get.invoke(internal, key);
    }
    
    public IPersistentVector<T> conj(T value) {
        return new PersistentVector<T>(Bridge.conj.invoke(internal, value));
    }
}
