package clojure4j.core;

// REVIEW quite possibly unnecessary
public abstract class AbstractSeq<T> extends AbstractPersistentCollection<T> implements ISeq<T> {
    
    protected AbstractSeq(Object internal) {
        super(internal);
    }

}
