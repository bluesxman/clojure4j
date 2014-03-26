package clojure4j.core;

import clojure.lang.IPersistentList;

// TODO: decide if want generic typing
public class PersistentList {
    private final clojure.lang.IPersistentList internal;
    
    public PersistentList(Object... elements) {
        internal = (IPersistentList) Core.list.invoke(elements);
    }
    
    public IPersistentList map(Object fn) {
        return (IPersistentList) Core.map.apply(fn, internal);
    }
    
    public Object apply(Object fn){
        return Core.apply.apply(fn, internal);
    }
}
