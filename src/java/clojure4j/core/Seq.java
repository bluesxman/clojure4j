package clojure4j.core;

public class Seq<T> extends AbstractSequential<T> implements ISeq<T>{
    Seq(Object internal) {
        super(internal);
    }

    @Override
    public final ISeq<T> conj(T value) {
        return new Seq<T>(Bridge.conj.invoke(getInternal(), value));
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ISeq<T> into(IPersistentCollection<T> from) {
        return (ISeq<T>) Bridge.into.invoke(getInternal(), from);
    }

    // REVIEW cache count? This may affect apply/applyTo performance and methods implementing SeqFn.
}
