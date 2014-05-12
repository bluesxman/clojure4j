package clojure4j.core;

import clojure.lang.IPersistentCollection;
import clojure.lang.ISeq;

class ArraySeq implements clojure.lang.ISeq {
    private static final ArraySeq EMPTY = new ArraySeq();
    private Object[] elements; 
    private int first;
    
    @SafeVarargs
    ArraySeq(Object... ts) {
        this(0, ts);
    }
    
    ArraySeq(int first, Object... ts) {
        elements = ts;
        this.first = first;
    }

    @Override
    public int count() {
        return elements.length;
    }

    @Override
    public IPersistentCollection empty() {
        return EMPTY;
    }

    @Override
    public boolean equiv(Object that) { // REVIEW check clojure's contract for equiv
        if(this == that) return true;
        if(that instanceof ArraySeq) {
            if(elements.length == ((ArraySeq) that).elements.length) {
                for(int i = 0; i < elements.length; i++) {
                    if(!elements[i].equals(((ArraySeq) that).elements[i])) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    @Override
    public final boolean equals(Object obj) {
        return equiv(obj);
    }

    @Override
    public ISeq seq() {
        return this;
    }

    @Override
    public ISeq cons(Object arg0) {
        return (ISeq) Bridge.cons.invoke(arg0, this);
    }

    private final boolean isEmpty() {
        return first >= elements.length;
    }
    
    private final boolean isRestEmpty() {
        return first >= elements.length - 1;
    }

    @Override
    public Object first() {
        return isEmpty() ? null : elements[first];
    }

    @Override
    public final ISeq more() {
        return isRestEmpty() ? EMPTY : new ArraySeq(first + 1, elements);  // REVIEW this used for rest???
    }

    @Override
    public ISeq next() {
        return isRestEmpty() ? null : new ArraySeq(first + 1, elements);
    }
    
    Object[] toArray() {
        return elements;
    }

}
