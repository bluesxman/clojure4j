package clojure4j.core;

public class Seq<T> extends AbstractSequential<T> implements ISeq<T>{
    Seq(Object internal) {
        super(internal);
    }

    @Override
    public final ISeq<T> conj(T value) {
        return new Seq<T>(Bridge.conj.invoke(getInternal(), value));
    }
}
