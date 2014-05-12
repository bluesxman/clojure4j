package clojure4j.core;

import java.util.NoSuchElementException;

public class ArrayVarArgs<T> implements IVarArgs<T> {
    private T[] args;
    private int index;
    
    @SuppressWarnings("unchecked")
    public ArrayVarArgs(T... args) {
        this(0, args);
    }
    
    @SuppressWarnings("unchecked")
    private ArrayVarArgs(int index, T... args) {
        this.args = args;
        this.index = index;
    }

    @Override
    public T head() {
        if(hasElements()) {
            return args[index];
        }
        else {
            throw new NoSuchElementException("Cannot call head on empty IVarArgs.");
        }
    }

    @Override
    public IVarArgs<T> tail() {
        if(hasElements()) {
            return new ArrayVarArgs<T>(index + 1, args);
        }
        else {
            throw new NoSuchElementException("Cannot call tail on empty IVarArgs.");
        }
    }

    @Override
    public boolean hasElements() {
        return index >= args.length;
    }

}
