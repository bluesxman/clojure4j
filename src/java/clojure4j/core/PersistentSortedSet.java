package clojure4j.core;

public class PersistentSortedSet<T> 
    extends AbstractPersistentCollection<T>
    implements IPersistentSet<T> {

    public PersistentSortedSet() {
        this(Bridge.hashSet.invoke());
    }

    public PersistentSortedSet(Object set) {
        super(set);
    }

    @SafeVarargs
    public PersistentSortedSet(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }

    @Override
    public PDSType getType() {
        return PDSType.SortedSet;
    }    
    
    @Override
    public IPersistentSet<T> conj(T value) {
        return new PersistentSortedSet<T>(Bridge.conj.invoke(getInternal(), value));
    }

}
