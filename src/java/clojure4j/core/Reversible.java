package clojure4j.core;

public interface Reversible<T> {
    public ISeq<T> reverse();
    public ISeq<T> rseq();
}
