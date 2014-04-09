package clojure4j.core;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

class Bridge {
    
    
    // Create Persistent Data Structure
    public static final IFn hashMap = Clojure.var("clojure.core", "hash-map");
    public static final IFn hashSet = Clojure.var("clojure.core", "hash-set");
    public static final IFn list = Clojure.var("clojure.core", "list");
    public static final IFn sortedMap = Clojure.var("clojure.core", "sorted-map");
    public static final IFn sortedSet = Clojure.var("clojure.core", "sorted-set");
    public static final IFn vector = Clojure.var("clojure.core", "vector");
    
    // Atom
    public static final IFn atom = Clojure.var("clojure.core", "atom");
    public static final IFn deref = Clojure.var("clojure.core", "deref");
    public static final IFn swap = Clojure.var("clojure.core", "swap!");
    public static final IFn reset = Clojure.var("clojure.core", "reset!");
//    public static final IFn  = Clojure.var("clojure.core", "");
    
    // Transform value
    public static final IFn conj = Clojure.var("clojure.core", "conj");
    public static final IFn cons = Clojure.var("clojure.core", "cons");
    public static final IFn assoc = Clojure.var("clojure.core", "assoc");
    public static final IFn dissoc = Clojure.var("clojure.core", "dissoc");
    public static final IFn seq = Clojure.var("clojure.core", "seq");
    public static final IFn first = Clojure.var("clojure.core", "first");
    public static final IFn rest = Clojure.var("clojure.core", "rest");
    public static final IFn pop = Clojure.var("clojure.core", "pop");
    public static final IFn peek = Clojure.var("clojure.core", "peek");
    
    // Reads
    public static final IFn get = Clojure.var("clojure.core", "get");
    
    // Higher order fns
    public static final IFn map = Clojure.var("clojure.core", "map");
    public static final IFn apply = (Clojure.var("clojure.core", "apply"));
    public static final IFn filter = Clojure.var("clojure.core", "filter");
    public static final IFn remove = Clojure.var("clojure.core", "remove");

    public static final IFn count = Clojure.var("clojure.core", "count");
    
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
