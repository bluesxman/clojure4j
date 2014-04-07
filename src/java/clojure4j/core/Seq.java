package clojure4j.core;

public class Seq<T> extends AbstractSeq<T> {
    Seq(Object internal) {
        super(internal);
    }

    @Override
    public PDSType getType() {
        // TODO Auto-generated method stub
        return PDSType.Seq;
    }
    
    @Override
    public ISeq<T> cons(T value) {
        return new Seq<T>(Bridge.cons.invoke(value, getInternal()));
    }
}
