package clojure4j.core;

/**
 * Contains common fns for vector and list.
 */
public abstract class AbstractSequential<T> 
extends AbstractPersistentCollection<T> implements Sequential<T> {

    public AbstractSequential(Object internal) {
        super(internal);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T nth(int idx) {
        return (T) Bridge.nth.invoke(getInternal(), idx);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T nth(int idx, T notFound) {
        return (T) Bridge.nth.invoke(getInternal(), idx, notFound);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T nth(long idx) {
        return (T) Bridge.nth.invoke(getInternal(), idx);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T nth(long idx, T notFound) {
        return (T) Bridge.nth.invoke(getInternal(), idx, notFound);
    }
    
    @Override
    public boolean containsIndex(long idx) {
        return (boolean) Bridge.contains.invoke(getInternal(), idx);
    }
}
