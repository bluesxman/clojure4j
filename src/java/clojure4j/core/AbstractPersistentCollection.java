package clojure4j.core;

import java.util.Comparator;


public abstract class AbstractPersistentCollection<T> 
extends AbstractInternal
implements IPersistentCollection<T> {
    
    public AbstractPersistentCollection(Object internal) {
        super(internal);
    }
    
    protected ISeq<T> wrapSeq(Object internal) {
        return new Seq<>(internal);
    }
    
    @Override
    public ISeq<T> filter(UnaryFn<T, Boolean> pred) {
        return wrapSeq(Bridge.filter.invoke(pred, internal));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <R> ISeq<R> map(UnaryFn<T, R> fn) {
        clojure.lang.ISeq seq = (clojure.lang.ISeq) Bridge.map.invoke(fn, internal);
        if(seq.count() > 0 && seq.first() instanceof clojure.lang.IMapEntry) {
            return (ISeq<R>) new EntrySeq(seq);
        } else {
            return new Seq<>(seq);
        }
    }
    
//    public <K,V> ISeq<IMapEntry<K,V>> map(UnaryFn<T, IMapEntry<K,V>> fn) {
//        return new EntrySeq<>(Bridge.map.invoke(fn, internal));
//    }
//    
    @Override
    public ISeq<T> cons(T value) {
        return wrapSeq(Bridge.cons.invoke(value, getInternal()));
    }
    
//    @SuppressWarnings("hiding")
//    public <T extends Internal> ISeq<T> cons(T value) {
//        return new Seq<T>(Bridge.cons.invoke(value.getInternal(), getInternal()));        
//    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> R apply(ApplySeqFn<T, R> fn) {
        return (R) Bridge.apply.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R extends T> R reduce(BinaryFn<T, T, R> fn) {
        return (R) Bridge.reduce.invoke(fn, getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <U> U reduce(BinaryFn<U, T, U> fn, U initial) {
        return (U) Bridge.reduce.invoke(fn, initial, getInternal());
    }
    
    @Override
    public int count() {
        return (int) Bridge.count.invoke(getInternal());
    }
    
    @Override
    public boolean isEmpty() {
        return (boolean) Bridge.isEmpty.invoke(getInternal());
    }
    
    @Override
    public boolean isSome(UnaryFn<T,Boolean> fn) {
        return Bridge.isSome.invoke(fn, getInternal()) != null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T first() {
        return (T) Bridge.first.invoke(getInternal());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T second() {
        return (T) Bridge.second.invoke(getInternal());
    }
    
    @Override 
    public ISeq<T> rest() {
        return wrapSeq(Bridge.rest.invoke(getInternal()));
    }

    @Override
    public ISeq<T> seq() {
        return isEmpty() ? null : wrapSeq(Bridge.seq.invoke(getInternal()));
    }
    
    @Override
    public ISeq<T> cycle() {
        return wrapSeq(Bridge.cycle.invoke(getInternal()));
    }

    @Override
    public ISeq<T> take(int n) {
        return wrapSeq(Bridge.take.invoke(n, getInternal()));
    }
    
    @Override
    public ISeq<T> takeWhile(UnaryFn<? super T, Boolean> pred) {
        return wrapSeq(Bridge.takeWhile.invoke(pred, getInternal()));
    }

    @Override
    public ISeq<T> drop(int n) {
    	return wrapSeq(Bridge.drop.invoke(n, getInternal()));
    }
    
    @Override
    public ISeq<T> dropWhile(UnaryFn<? super T, Boolean> pred) {
    	return wrapSeq(Bridge.dropWhile.invoke(pred, getInternal()));
    }
    
    @Override
    public ISeq<T> distinct() {
        return wrapSeq(Bridge.distinct.invoke(getInternal()));
    }

    @Override
    public ISeq<T> sort(Comparator<T> comp) {
        return wrapSeq(Bridge.sort.invoke(comp, getInternal()));
    }
    
    @Override
    public ISeq<T> concat(IPersistentCollection<T> col) {
    	return wrapSeq(Bridge.concat.invoke(this.getInternal(), col.getInternal()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public ISeq<T> concat(IPersistentCollection<T>... colls) {
    	//REVIEW: More efficient solution
    	if(colls.length == 0)
    		return (new PersistentList<T>()).seq();
    	else {
    		ISeq<T> rval = this.seq();

    		for(int i = 1; i < colls.length; i++) {
    			rval = concat(rval, colls[i]);
    		}

    		return rval;
    	}
    }
    
    @Override
    public <U> ISeq<U> flatten() {
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> ISeq<U> mapcat(UnaryFn<T, U> fn){
        UnaryFn<T, ISeq<U>> c = Core::concat;
        return Core.reduce(c, null, map(fn));
    }

    
    @Override
    public ISeq<T> reverse() {
    	return wrapSeq(Bridge.reverse.invoke(getInternal()));
    }

}
