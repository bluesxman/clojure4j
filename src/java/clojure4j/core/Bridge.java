package clojure4j.core;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

class Bridge {
    public static final IFn require = Clojure.var("clojure.core", "require");
    
    // Have to explicitly require namespaces other than clojure.core
    static {
        require.invoke(Clojure.read("clojure.set"));
    }
    
    // Create Persistent Data Structure
    public static final IFn hashMap = Clojure.var("clojure.core", "hash-map");
    public static final IFn hashSet = Clojure.var("clojure.core", "hash-set");
    public static final IFn list = Clojure.var("clojure.core", "list");
    public static final IFn sortedMap = Clojure.var("clojure.core", "sorted-map");
    public static final IFn sortedMapBy = Clojure.var("clojure.core", "sorted-map-by");
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
    public static final IFn take = Clojure.var("clojure.core", "take");
    
    // Reads
    public static final IFn get = Clojure.var("clojure.core", "get");
    
    // Higher order fns
    public static final IFn map = Clojure.var("clojure.core", "map");
    public static final IFn apply = (Clojure.var("clojure.core", "apply"));
    public static final IFn reduce = (Clojure.var("clojure.core", "reduce"));
    public static final IFn filter = Clojure.var("clojure.core", "filter");
    public static final IFn remove = Clojure.var("clojure.core", "remove");

    public static final IFn count = Clojure.var("clojure.core", "count");
    
    // Set fns
    public static final IFn join = Clojure.var("clojure.set", "join");
    public static final IFn select = Clojure.var("clojure.set", "select");
    public static final IFn project = Clojure.var("clojure.set", "project");
    public static final IFn union = Clojure.var("clojure.set", "union");
    public static final IFn difference = Clojure.var("clojure.set", "difference");
    public static final IFn intersection = Clojure.var("clojure.set", "intersection");
    public static final IFn disj = Clojure.var("clojure.core", "disj");
    public static final IFn isSubset = Clojure.var("clojure.set", "subset?");
    public static final IFn isSuperset = Clojure.var("clojure.set", "superset?");
    public static final IFn contains = Clojure.var("clojure.core", "contains?");

    // Map Fns
    public static final IFn selectKeys = Clojure.var("clojure.core", "select-keys");
    public static final IFn keys = Clojure.var("clojure.core", "keys");
    public static final IFn vals = Clojure.var("clojure.core", "vals");
    public static final IFn find = Clojure.var("clojure.core", "find");

    // Vector fns
    public static final IFn subvec = Clojure.var("clojure.core", "subvec");
    public static final IFn replace = Clojure.var("clojure.core", "replace");
    public static final IFn nth = Clojure.var("clojure.core", "nth");
    public static final IFn reverse = Clojure.var("clojure.core", "reverse");
    public static final IFn rseq = Clojure.var("clojure.core", "rseq");

    // ranges, repetition, infinite seqs
    public static final IFn repeat = Clojure.var("clojure.core", "repeat");
    public static final IFn range = Clojure.var("clojure.core", "range");
    public static final IFn iterate = Clojure.var("clojure.core", "iterate");
    public static final IFn repeatedly = Clojure.var("clojure.core", "repeatedly");
    public static final IFn cycle = Clojure.var("clojure.core", "cycle");

    //    public static final IFn  = Clojure.var("clojure.core", "");
}
