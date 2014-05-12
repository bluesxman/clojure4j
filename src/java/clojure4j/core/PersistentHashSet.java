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
        super(Bridge.apply.invoke(Bridge.hashSet, new ArraySeq(elements)));
    }
    
    public PersistentHashSet(IPersistentCollection<T> elements) {
        super(Bridge.apply.invoke(Bridge.hashSet, elements.getInternal()));
    }
    
    @Override
    public IPersistentSet<T> wrap(Object internal) {
        return new PersistentHashSet<T>(internal);
    }

}
