package clojure4j.core;

/**
 * An immutable finite list of zero or more arguments.  Arguments may be null.
 * 
 * @param <T> The type of all arguments in the argument list.
 */
public interface IVarArgs<T> {
    /**
     * @return The first element in the argument list.
     * @throws java.util.NoSuchElementException if list is empty
     */
    public T head();
    
    /**
     * @return All the arguments minus the first element or an empty argument list if the current list is empty.
     * @throws java.util.NoSuchElementException if list is empty
     */
    public IVarArgs<T> tail();

    /**
     * @return True if the list contains one or more arguments.
     */
    public boolean hasElements();
}
