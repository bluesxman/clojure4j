package clojure4j.core;

public abstract class AbstractInternal implements Internal {

    protected final Object internal;
    
    AbstractInternal(Object internal) {
        this.internal = internal;
    }

    @Override
    public final Object getInternal() {
        return internal;
    }
    
    @Override
    public String toString() {
        return getInternal() == null ? "null" : getInternal().toString();
    }
        
    @Override
    public boolean equals(Object o) {
        if(o instanceof Internal) {
            return (boolean) Bridge.eq.invoke(this.getInternal(), ((Internal) o).getInternal());
        }
        else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return getInternal().hashCode();
    }
}
