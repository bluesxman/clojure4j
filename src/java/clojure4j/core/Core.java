package clojure4j.core;

import java.util.Comparator;


public final class Core {
    
    // REVIEW edge cases for varargs not considered?
    @SafeVarargs
    public static final <T> IPersistentList<T> list(T... elements){
        return new PersistentList<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentVector<T> vector(T... elements){
        return new PersistentVector<T>(elements);
    }
    
    @SafeVarargs
    public static final <T> IPersistentSet<T> hashSet(T... elements){
        return new PersistentHashSet<T>(elements);
    }

    @SafeVarargs
    public static final <T> IPersistentSet<T> sortedSet(T... elements){
        return new PersistentSortedSet<T>(elements);
    }
    
    public static final <K, V> IPersistentMap<K, V> hashMap(K key, V val) {
        return new PersistentHashMap<K, V>(key, val);
    }

    public static final <K, V> IPersistentMap<K, V> sortedMap(K key, V val) {
        return new PersistentSortedMap<K, V>(key, val);
    }

    public static final <T, R> ISeq<R> map(UnaryFn<T, R> fn, IPersistentCollection<T> col) {
        return new Seq<R>((clojure.lang.ISeq) Bridge.map.invoke(fn, col.getInternal()));
    }
    
    public static final <T> ISeq<T> filter(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.filter.invoke(pred, col.getInternal()));
    }
    
    public static final <T> ISeq<T> remove(UnaryFn<T, Boolean> pred, IPersistentCollection<T> col) {
        return new Seq<T>((clojure.lang.ISeq) Bridge.remove.invoke(pred, col.getInternal()));
    }
    
    public static final <T, R> R apply(BinaryFn<T, T, R> fn, IPersistentCollection<T> col) {
        return col.apply(fn);
    }

    public static final <T, R> R reduce(BinaryFn<T, T, R> fn, IPersistentCollection<T> col) {
        return col.reduce(fn);
    }
    
    public static final <T, R> R reduce(BinaryFn<T, T, R> fn, T initial, IPersistentCollection<T> col) {
        return col.reduce(fn, initial);
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E value) {
        return (T) col.conj(value);
    }
    
//    @SuppressWarnings("unchecked")
//    public static final <T extends IPersistentCollection<E>, E> T conj(T col, E... values) {
//        return (T) col.conj(values);
//    }
//    
    public static final <T extends IPersistentCollection<E>, E> ISeq<E> cons(E value, T col) {
        return col.cons(value);
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
    
    public static final <T> IPersistentCollection<T> rest(IPersistentCollection<T> col) {
        return col == null ? null : col.rest();
    }
    
    public static final <T> ISeq<T> seq(IPersistentCollection<T> col) {
        return col == null ? null : col.seq();
    }
    
    public static final boolean isOdd(int n) {
        return n % 2 == 0; // TODO use faster method than modulo
    }
    
    public static final boolean isOdd(long n) {
        return n % 2 == 0;
    }
    
    public static final boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static final boolean isEven(long n) {
        return n % 2 == 0;
    }

    // TODO: Does F# have an equiv?  If so, what's the typing look like?
//    public <K, V> IPersistentMap<K, V> assocIn(IPersistentMap<K, V> map, IPersistentVector<K> ks, V value){}
//    public <K, V> IPersistentMap<K, V> updateIn(IPersistentMap<K, V> map, IPersistentVector<K> ks, UnaryFn<V, V> fn){}
//    public V getIn(IPersistentVector<K> ks);

    public static final <T> ISeq<T> repeat(T value) {
        return new Seq<>(Bridge.repeat.invoke(value));
    }

    public static final <T extends Number> ISeq<T> range(T num) {
        return new Seq<>(Bridge.range.invoke(num));
    }

    public static final <T extends Number> ISeq<T> range(T num, int end) {
        return new Seq<>(Bridge.range.invoke(num, end));
    }

    public static final <T extends Number> ISeq<T> range(T num, int start, int end) {
        return new Seq<>(Bridge.range.invoke(num, start, end));
    }

    public static final <T extends Number> ISeq<T> range(T num, int start, int end, int step) {
        return new Seq<>(Bridge.range.invoke(num, start, end, step));
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
        return n++;
    }
    
    public static final int dec(int n) {
        return n--;
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
    
    public static final boolean isNeg(int n) {
        return n < 0;
    }
    
    public static final boolean isPos(int n) {
        return n > 0;
    }
}
