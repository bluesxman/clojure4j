package clojure4j.core;



public final class PersistentVector<T> 
    extends AbstractPersistentVector<T>{

    public PersistentVector() {
        super(Bridge.vector.invoke());
    }
    
    public PersistentVector(Object vec) {
        super(vec);
    }
    
    @SafeVarargs
    public PersistentVector(T... elements) {
        super(Util.arrayToClojureVector(elements));
    }
    
    @Override
    public PDSType getType() {
        return PDSType.Vector;
    }

}