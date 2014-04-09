package clojure4j.core;


public class PersistentHashSet<T> 
    extends AbstractPersistentCollection<T>
    implements IPersistentSet<T> {

    public PersistentHashSet() {
        this(Bridge.hashSet.invoke());
    }

    public PersistentHashSet(Object set) {
        super(set);
    }

    @SafeVarargs
    public PersistentHashSet(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }
    
    @Override
    public IPersistentSet<T> conj(T value) {
        return new PersistentHashSet<T>(Bridge.conj.invoke(getInternal(), value));
    }

}
