package clojure4j.core;


public abstract class AbstractPersistentSet<T> extends AbstractPersistentCollection<T> implements IPersistentSet<T> {

    public AbstractPersistentSet(Object set) {
        super(set);
    }

    @SafeVarargs
    public AbstractPersistentSet(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }

    @Override
    public IPersistentSet<T> conj(T value) {
        return wrap(Bridge.conj.invoke(getInternal(), value));
    }

    @Override
    public IPersistentSet<T> disj(T value) {
        return wrap(Bridge.disj.invoke(getInternal(), value));
    }

    @Override
    public IPersistentSet<T> disj(@SuppressWarnings("unchecked") T... values) {
        Object internal = getInternal();
        
        for(T val : values) {
            internal = Bridge.disj.invoke(internal, val);
        }
        
        return wrap(internal);
    }

//    @Override
//    public <K, V, T extends IPersistentMap<K, V>> IPersistentSet<T> join(IPersistentSet<T> set) {
//        return new PersistentHashSet<T>(Bridge.join.invoke(getInternal()));
//    }

    @Override
    public IPersistentSet<T> select(UnaryFn<T, Boolean> pred) {
        return wrap(Bridge.select.invoke(pred, getInternal()));
    }

//    @Override
//    public IPersistentSet<T> project() {
//        return new PersistentHashSet<T>(Bridge..invoke(getInternal(), value));
//    }

    @Override
    public IPersistentSet<T> union(IPersistentSet<T> set2) {
        return wrap(Bridge.union.invoke(getInternal(), set2.getInternal()));
    }

    @Override
    public IPersistentSet<T> difference(IPersistentSet<T> set2) {
        return wrap(Bridge.difference.invoke(getInternal(), set2.getInternal()));
    }

    @Override
    public IPersistentSet<T> intersection(IPersistentSet<T> set2) {
        return wrap(Bridge.intersection.invoke(getInternal(), set2.getInternal()));
    }

    @Override
    public boolean isSubset(IPersistentSet<T> set2) {
        return (boolean) Bridge.isSubset.invoke(getInternal(), set2.getInternal());
    }

    @Override
    public boolean isSuperset(IPersistentSet<T> set2) {
        return (boolean) Bridge.isSuperset.invoke(getInternal(), set2.getInternal());
    }

    @Override
    public boolean contains(T value) {
        return (boolean) Bridge.contains.invoke(getInternal(), value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IPersistentSet<T> into(IPersistentCollection<T> from) {
        return (IPersistentSet<T>) Bridge.into.invoke(getInternal(), from);
    }

}
