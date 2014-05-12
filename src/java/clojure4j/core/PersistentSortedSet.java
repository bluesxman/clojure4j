package clojure4j.core;

public class PersistentSortedSet<T> extends AbstractPersistentSet<T> {

    public PersistentSortedSet() {
        this(Bridge.sortedSet.invoke());
    }

    public PersistentSortedSet(Object set) {
        super(set);
    }
    
    public PersistentSortedSet(IPersistentCollection<T> elements) {
        super(Bridge.apply.invoke(Bridge.sortedSet, elements.getInternal()));
    }

    @SafeVarargs
    public PersistentSortedSet(T... elements) {
        super(Bridge.apply.invoke(Bridge.sortedSet, new ArraySeq(elements)));
    }
    
    @Override
    public IPersistentSet<T> wrap(Object internal) {
        return new PersistentSortedSet<T>(internal);
    }
}
