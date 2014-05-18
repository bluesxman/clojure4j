package clojure4j.core;

/**
 * The internal sequence will contain Clojure map entries which must be wrapped before return them.
 * 
 * @param <K>
 * @param <V>
 */
class EntrySeq<K,V> extends Seq<IMapEntry<K,V>> {

    EntrySeq(Object internal) {
        super(internal);
    }

    @Override
    public IMapEntry<K,V> first() {
        return new MapEntry<K,V>(super.first());
    }

    @Override
    public IMapEntry<K,V> second() {
        return new MapEntry<K,V>(super.second());
    }
    
    //REVIEW How well do maps play with conj, cons, rest, etc.
}
