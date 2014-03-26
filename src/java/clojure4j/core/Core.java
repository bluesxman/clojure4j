package clojure4j.core;

import java.util.function.BiFunction;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class Core {
    public static final IFn list = Clojure.var("clojure.core", "list");
    
    public static final BiFunction<Object, Object, Object> map = 
            Clojure.var("clojure.core", "map")::invoke;
    public static final BiFunction<Object, Object, Object> apply = 
            (Clojure.var("clojure.core", "apply"))::invoke;
    public static final IFn filter = Clojure.var("clojure.core", "filter");
//    public static final IFn  = Clojure.var("clojure.core", "");
    
    
    
    /*
     * Binding is just methods that are pass-throughs to a clojure type/record/protocol
     * that has all the right methods of interest.  Create a bunch of *4J version of the clojure 
     * interfaces in clojure and then use clojure's new Java API to bring them into Java.  
     * 
     * These clojure classes would impl the clojure protocols (<- that wont work)
     * plus read-only impls of java.util
     * 
     * If going with clojure core fns on all the java versions of PDS's, then may have to create
     * a conversion from java.util.functional to IFn.  In clojure would gen class impls of all the
     * java.util.functional interfaces that also impl IFn.  This only matters if we want to support
     * generics in the api.
     */
}
