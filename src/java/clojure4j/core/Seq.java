package clojure4j.core;

public class Seq<T> extends AbstractSeq<T> {
    Seq(Object internal) {
        super(internal);
    }

    @Override
    public final ISeq<T> cons(final T value) {
        return new Seq<T>(Bridge.cons.invoke(value, getInternal()));
    }

    @Override
    public final ISeq<T> conj(T value) {
        return new Seq<T>(Bridge.conj.invoke(getInternal(), value));
    }
}
