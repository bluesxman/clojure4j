package clojure4j.core;

final class Util {

    public static final Object arrayToClojureList(Object[] elements) {
        Object clojList = Bridge.list.invoke();
        for(int i = elements.length - 1; i >= 0; i--) {
            clojList = Bridge.conj.invoke(clojList, elements[i]);
        }
        return clojList;
    }
    
    public static final <T> Object arrayToClojureVector(Object[] elements) {
        return arrayToClojure(elements, Bridge.vector.invoke());
    }
    
    public static final Object arrayToClojureHashSet(Object[] elements) {
        return arrayToClojure(elements, Bridge.hashSet.invoke());
    }
    
    public static final Object arrayToClojureSortedSet(Object[] elements) {
        return arrayToClojure(elements, Bridge.sortedSet.invoke());
    }
    
    // REVIEW probably broken for list; should we make ArraySeq to wrap a T... and pass to clojure:
    // e.g. (apply (list xs))
    private static final Object arrayToClojure(Object[] elements, Object clojureColl) {
        for(Object element : elements) {
            clojureColl = Bridge.conj.invoke(clojureColl, element);
        }
        return clojureColl;        
    }
}
