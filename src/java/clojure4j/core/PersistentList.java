package clojure4j.core;

// TODO: decide if want generic typing
public class PersistentList<T> 
    extends AbstractSequential<T> 
    implements IPersistentList<T> {
	
    @SafeVarargs
    public PersistentList(T... elements) {
        super(arrayToClojureList(elements));  // REVIEW use ArraySeq?
    }
    
    public PersistentList(Object internal) {
        super(internal);
    }
    
    public PersistentList(ApplySeq<T> elements) {
        super(Bridge.apply.invoke(Bridge.list, elements.getInternal()));
    }
    
    private static final Object arrayToClojureList(Object[] elements) {
        Object clojList = Bridge.list.invoke();
        for(int i = elements.length - 1; i >= 0; i--) {
            clojList = Bridge.conj.invoke(clojList, elements[i]);
        }
        return clojList;
    }
    
    public PersistentList(clojure.lang.ISeq seq) {
        super(seq);
    }
    
    public PersistentList() {
    	super(Bridge.EMPTY_LIST);
    }
    
    @Override 
    public final IPersistentList<T> conj(final T value) {
        return new PersistentList<T>(Bridge.conj.invoke(getInternal(), value));
    }

    @Override
    public IPersistentList<T> pop() {
        return new PersistentList<>(Bridge.pop.invoke(getInternal()));
    }
    
    @Override
    public IPersistentList<T> into(IPersistentCollection<T> from) {
        return new PersistentList<T>(Bridge.into.invoke(getInternal(), from.getInternal()));
    }
}
