package clojure4j.core;

class ClojureVarArgs<T> extends Seq<T>{

    public ClojureVarArgs(clojure.lang.ISeq args) {
        super(args);
    }
}
