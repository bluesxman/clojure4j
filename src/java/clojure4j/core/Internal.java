package clojure4j.core;

interface Internal {
    enum PDSType {
        List,
        HashMap,
        SortedMap,
        HashSet,
        Seq,
        SortedSet,
        Vector
    };
    
    Object getInternal();
    PDSType getType();
}
