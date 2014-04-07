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
    public PDSType getType() {
        return PDSType.HashSet;
    }
    
    @Override 
    public IPersistentSet<T> cons(T value) {
        return new PersistentHashSet<T>(Bridge.cons.invoke(value, getInternal()));
    }



}
