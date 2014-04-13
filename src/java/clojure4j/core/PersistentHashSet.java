package clojure4j.core;

public class PersistentHashSet<T> extends AbstractPersistentSet<T> {

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
    public IPersistentSet<T> wrap(Object internal) {
        return new PersistentHashSet<T>(internal);
    }

}
