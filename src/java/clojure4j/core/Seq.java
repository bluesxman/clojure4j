package clojure4j.core;

public class Seq<T> extends AbstractSeq<T> {
    public Seq(clojure.lang.ISeq clojSeq) {
        super(clojSeq);
    }
}
