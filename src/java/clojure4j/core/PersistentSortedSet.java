package clojure4j.core;

public class PersistentSortedSet<T> extends AbstractPersistentSet<T> {

    public PersistentSortedSet() {
        this(Bridge.sortedSet.invoke());
    }

    public PersistentSortedSet(Object set) {
        super(set);
    }

    @SafeVarargs
    public PersistentSortedSet(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }

    @Override
    public IPersistentSet<T> wrap(Object internal) {
        return new PersistentSortedSet<T>(internal);
    }
}
