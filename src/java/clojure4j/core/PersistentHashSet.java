package clojure4j.core;

public class PersistentHashSet<T> extends AbstractPersistentCollection<T> {

    public PersistentHashSet() {
        this(Bridge.hashSet.invoke());
    }

    public PersistentHashSet(Object set) {
        super(set);
    }
}
