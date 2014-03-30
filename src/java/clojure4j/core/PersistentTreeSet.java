package clojure4j.core;

public class PersistentTreeSet<T> extends AbstractPersistentCollection<T> {

    public PersistentTreeSet() {
        this(Bridge.sortedSet.invoke());
    }

    public PersistentTreeSet(Object set) {
        super(set);
    }
}
