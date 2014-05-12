package clojure4j.core;

import java.util.Comparator;


public final class Core {
    
    // REVIEW edge cases for varargs not considered?
    @SafeVarargs
    public static final <T> IPersistentList<T> list(T... elements){
        return new PersistentList<T>(elements);
    }
    
    public static final <T> IPersistentList<T> list(ApplySeq<T> elements){
        return new PersistentList<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentVector<T> vector(T... elements){
        return new PersistentVector<T>(elements);
    }
    
    public static final <T> IPersistentVector<T> vector(ApplySeq<T> elements){
        return new PersistentVector<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> hashSet(T... elements){
        return new PersistentHashSet<T>(elements);
    }

    public static final <T> IPersistentSet<T> hashSet(ApplySeq<T> elements){
        return new PersistentHashSet<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> sortedSet(T... elements){
        return new PersistentSortedSet<T>(elements);
    }
    
    public static final <T> IPersistentSet<T> sortedSet(ApplySeq<T> elements){
        return new PersistentSortedSet<T>(elements);
    }
    
    public static final <K, V> IPersistentMap<K, V> hashMap(K key, V val) {
        return new PersistentHashMap<K, V>(key, val);
    }

    @SafeVarargs
    public static final <K, V> IPersistentMap<K, V> hashMap(IMapEntry<K, V>... entries) {
        return new PersistentHashMap<>(entries);
    }
    
    public static final <K, V> IPersistentMap<K, V> sortedMap(K key, V val) {
        return new PersistentSortedMap<K, V>(key, val);
    }

    @SafeVarargs
    public static final <K, V> IPersistentMap<K, V> sortedMap(IMapEntry<K, V>... entries) {
        return new PersistentSortedMap<K, V>(entries);
    }
    
    public static final <T, R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T> col) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, col.getInternal()));
    }
    
    public static final <T, U, R> ISeq<R> map(
            BinaryFn<T, U, R> fn, 
            IPersistentCollection<T> col1,
            IPersistentCollection<U> col2) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, col1.getInternal(), col2.getInternal()));
    }
    
    public static final <T, R> ISeq<R> map(
            TernaryFn<T, T, T, R> fn, 
            IPersistentCollection<T> col1,
            IPersistentCollection<T> col2,
            IPersistentCollection<T> col3) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(
                fn, 
                col1.getInternal(), 
                col2.getInternal(), 
                col3.getInternal()));
    }
        
    public static final <T> ISeq<T> filter(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>(Bridge.filter.invoke(pred, col.getInternal()));
    }
    
    public static final <T> ISeq<T> remove(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.remove.invoke(pred, col.getInternal()));
    }
    
    public static final <T, R extends T> R apply(BinaryFn<T, T, R> fn, IPersistentCollection<T> col) {
        return col.apply(fn);
    }

    public static final <T, R> R apply(ApplySeqFn<T, R> fn, IPersistentCollection<T> col) {
        return col.apply(fn);
    }

    @SafeVarargs
    public static final <T, R> R apply(ApplySeqFn<T, R> fn, T... args) {
        return list(args).apply(fn);  // TODO Use special collection for arrays or reuse clojure arrayseq or something?
    }
    
    public static final <T> T reduce(BinaryFn<T, T, T> fn, IPersistentCollection<T> col) {
        return col.reduce(fn);
    }
    
    public static final <T, U> U reduce(BinaryFn<U, T, U> fn, U initial, IPersistentCollection<T> col) {
        return col.reduce(fn, initial);
    }
    
    // REVIEW what about doing 1 method per type of persistent collection
    public static final <T> IPersistentCollection<T> conj(IPersistentCollection<T> col, T value) {
        return col != null ? col.conj(value) : list(value);
    }
    
    public static final <T> IPersistentSet<T> conj(IPersistentSet<T> col, T value) {
        return col != null ? col.conj(value) : hashSet(value);
    }
    
    
    public static final <K, V> IPersistentMap<K, V> conj(IPersistentMap<K, V> col, IMapEntry<K, V> value) {
        return col != null ? col.conj(value) : hashMap(value);
    }
    
    public static final <T> IPersistentMap<T, T> conj(IPersistentMap<T, T> map, IPersistentVector<T> vec) {
        if(vec.count() == 2) {
            return map.assoc(vec.get(0L), vec.get(1L));
        }
        else {
            throw new IllegalArgumentException("vec must have two elements");
        }
    }
    
    public static final <T> IPersistentVector<T> conj(IPersistentVector<T> col, T value) {
        return col != null ? col.conj(value) : vector(value);
    }
    
    
    public static final <T> ISeq<T> conj(ISeq<T> col, T value) {
        return col != null ? col.conj(value) : new Seq<>(value);
    }
    
//    @SuppressWarnings("unchecked")
//    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E... values) {
//        return (T) col.conj(values);
//    }
//    
    public static final <T extends IPersistentCollection<E>, E> ISeq<E> cons(E value, T col) {
        return col.cons(value);
    }
    
    public static final <T> IPersistentList<T> into(IPersistentList<T> to, IPersistentCollection<T> from) {
        return to.into(from);
    }
    
    public static final <K, V> 
    IPersistentMap<K, V> into(IPersistentMap<K, V> to, IPersistentCollection<IMapEntry<K, V>> from) {
        return to.into(from);
    }
    
    public static final <T> IPersistentVector<T> into(IPersistentVector<T> to, IPersistentCollection<T> from) {
        return to.into(from);
    }
    
    public static final <T> IPersistentSet<T> into(IPersistentSet<T> to, IPersistentCollection<T> from) {
        return to.into(from);
    }
    
    public static final <T> IPersistentSet<T> disj(IPersistentSet<T> set, T key) {
        return set.disj(key);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> disj(IPersistentSet<T> set, T... keys) {
        return set.disj(keys);
    }
    
    public static final <T> boolean contains(IPersistentSet<T> set, T value) {
        return set.contains(value);
    }
    
    // TODO Figure out best policy on generics
    @SuppressWarnings("unchecked")
    public static final <K, V, T extends Associative<K, V>, KK extends K, VV extends V>
    T assoc(T col, KK key, VV val) {
        return (T) col.assoc(key, val);
    }
    
    public static final <T> int count(IPersistentCollection<T> col) {
        return col == null ? 0 : col.count();
    }
    
    public static final <T> T first(IPersistentCollection<T> col) {
        return col == null ? null : col.first();
    }
    
    public static final <T> T second(IPersistentCollection<T> col) {
        return col == null ? null : col.second();
    }
    
    public static final <K, V> K first(IMapEntry<K,V> entry) {
        return entry == null ? null : entry.first();
    }
    
    public static final <K, V> V second(IMapEntry<K,V> entry) {
        return entry == null ? null : entry.second();
    }
    
    public static final <T> ISeq<T> rest(IPersistentCollection<T> col) {
        return col == null ? null : col.rest();
    }
    
    public static final <T> ISeq<T> seq(IPersistentCollection<T> col) {
        return col == null ? null : col.seq();
    }
    
    public static final <K, V> ISeq<K> keys(IPersistentMap<K, V> map) {
        return map.keys();
    }
    
    public static final <K, V> ISeq<V> vals(IPersistentMap<K, V> map) {
        return map.vals();
    }
    
    public static final <K, V> V get(Associative<K, V> assoc, K key) {
        return assoc.get(key);
    }
    
    // REVIEW double check generics doc and confirm that IPersistentVector doesnt extend Associative in this case
    public static final <T> T get(IPersistentVector<T> vec, long index) {
        return vec.get(index);
    }
    
    public static final <T> T get(IPersistentVector<T> vec, int index) {
        return vec.get(index);
    }
    
    public static final <K, V> IMapEntry<K, V> find(IPersistentMap<K, V> map, K key) {
        return map.find(key);
    }
    
    public static final <T> T nth(Sequential<T> col, int i) {
        return col.nth(i);
    }
    
    public static final <T> T nth(Sequential<T> col, int i, T notFound) {
        return col.nth(i, notFound);
    }
    
    public static final <T> T nth(Sequential<T> col, long i) {
        return col.nth(i);
    }
    
    public static final <T> T nth(Sequential<T> col, long i, T notFound) {
        return col.nth(i, notFound);
    }
    
    // TODO: Does F# have an equiv?  If so, what's the typing look like?
//    public <K, V> IPersistentMap<K, V> assocIn(IPersistentMap<K, V> map, IPersistentVector<K> ks, V value){}
//    public <K, V> IPersistentMap<K, V> updateIn(IPersistentMap<K, V> map, IPersistentVector<K> ks, UnaryFn<V, V> fn){}
//    public V getIn(IPersistentVector<K> ks);

    public static final <T> ISeq<T> repeat(T value) {
        return new Seq<>(Bridge.repeat.invoke(value));
    }

    public static final ISeq<Long> range() {
        return new Seq<>(Bridge.range.invoke());
    }

    public static final ISeq<Long> range(long end) {
        return new Seq<>(Bridge.range.invoke(end));
    }

    public static final ISeq<Long> range(long start, long end) {
        return new Seq<>(Bridge.range.invoke(start, end));
    }

    public static final ISeq<Long> range(long start, long end, long step) {
        return new Seq<>(Bridge.range.invoke(start, end, step));
    }

    public static final <T> ISeq<T> iterate(UnaryFn<T, T> fn, T initial) {
        return new Seq<>(Bridge.iterate.invoke(fn, initial));
    }
    
    public static final <T> ISeq<T> repeatedly(NullaryFn<T> fn) {
        return new Seq<>(Bridge.repeatedly.invoke(fn));
    }
    
    public static final <T> ISeq<T> repeatedly(int length, NullaryFn<T> fn) {
        return new Seq<>(Bridge.repeatedly.invoke(length, fn));
    }
    
    public static final <T> ISeq<T> cycle(IPersistentCollection<T> col) {
        return col.cycle();
    }
    
    public static final <T> ISeq<T> reverse(Reversible<T> rev) {
        return rev.reverse();
    }
    
    public static final <T> ISeq<T> rseq(Reversible<T> rev) {
        return rev.rseq();
    }
    
    public static final <T> ISeq<T> take(int n, IPersistentCollection<T> col) {
        return col.take(n);
    }
    
    public static final <T> ISeq<T> takeWhile(UnaryFn<? super T, Boolean> pred, IPersistentCollection<T> col) {
        return col.takeWhile(pred);
    }

    public static final <T> ISeq<T> distinct(IPersistentCollection<T> col) {
        return col.distinct();
    }

    public static final <T extends Comparable<T>> int compare(T x, T y) {
        return x.compareTo(y);
    }
    
    public static final <T extends Comparable<T>> ISeq<T> sort(IPersistentCollection<T> col) {
        return new Seq<>(Bridge.sort.invoke(col));
    }

    public static final <T> ISeq<T> sort(Comparator<T> comp, IPersistentCollection<T> col) {
        return col.sort(comp);
    }
    
    public static final int add(int x, int y) {
        return x + y;
    }
        
    public static final int add(int... ints) {
        int result = 0;
        for(int i : ints) {
            result += i;
        }
        return result;
    }
    
    public static final int sub(int... ints) {
        if(ints.length > 0) {
            int result = 0;
            for(int i : ints) {
                result -= i;
            }
            return result;
        }
        else {
            throw new IllegalArgumentException("1 or more arguments expected");
        }
    }
    
    public static final int mul(int... ints) {
        int result = 1;
        for(int i : ints) {
            result *= i;
        }
        return result;
    }
    
    public static final int div(int... ints) {
        if(ints.length > 0) {
            int result = 1;
            for(int i : ints) {
                result /= i;
            }
            return result;
        }
        else {
            throw new IllegalArgumentException("1 or more arguments expected");
        }
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Number> double add(T... nums) {
        double result = 0;
        for(T i : nums) {
            result += i.doubleValue();
        }
        return result;        
    }
    
    public static final long add(long... nums) {
        int result = 0;
        for(long i : nums) {
            result += i;
        }
        return result;
    }
    
    public static final int inc(int n) {
        return n + 1;
    }
    
    public static final int dec(int n) {
        return n - 1;
    }
    
    public static final int min(int x, int y) {
        return x < y ? x : y;
    }
    
    public static final int max(int x, int y) {
        return x > y ? x : y;
    }
    
    public static final boolean isZero(int n) {
        return n == 0;
    }
    
    public static final boolean isZero(long n) {
        return n == 0;
    }
    
    public static final boolean isZero(float n) {
        return n == 0;
    }
    
    public static final boolean isZero(double n) {
        return n == 0;
    }
    
    public static final boolean isNeg(int n) {
        return n < 0;
    }
    
    public static final boolean isNeg(long n) {
        return n < 0;
    }
    
    public static final boolean isNeg(float n) {
        return n < 0;
    }
    
    public static final boolean isNeg(double n) {
        return n < 0;
    }
    
    public static final boolean isPos(int n) {
        return n > 0;
    }
    
    public static final boolean isPos(long n) {
        return n > 0;
    }
    
    public static final boolean isPos(float n) {
        return n > 0;
    }
    
    public static final boolean isPos(double n) {
        return n > 0;
    }
    
    public static final boolean isNull(Object o) {
        return o == null;
    }
    
    public static final boolean isOdd(int n) {
        return Math.abs(n % 2) == 0; // TODO use faster method than modulo
    }
    
    public static final boolean isOdd(long n) {
        return Math.abs(n % 2) == 0;
    }
    
    public static final boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static final boolean isEven(long n) {
        return n % 2 == 0;
    }

    public static final <T> boolean isEmpty(IPersistentCollection<T> col) {
        return col.isEmpty();
    }
    
    public static final <T> boolean isSome(UnaryFn<T,Boolean> fn, IPersistentCollection<T> col) {
        return col.isSome(fn);
    }
}
