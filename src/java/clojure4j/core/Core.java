package clojure4j.core;

public final class Core {
    public static final <T, R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T> col) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, col));
    }
}
