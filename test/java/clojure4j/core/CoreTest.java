package clojure4j.core;

import static clojure4j.core.Core.apply;
import static clojure4j.core.Core.conj;
import static clojure4j.core.Core.contains;
import static clojure4j.core.Core.count;
import static clojure4j.core.Core.dec;
import static clojure4j.core.Core.disj;
import static clojure4j.core.Core.first;
import static clojure4j.core.Core.get;
import static clojure4j.core.Core.hashMap;
import static clojure4j.core.Core.hashSet;
import static clojure4j.core.Core.inc;
import static clojure4j.core.Core.isZero;
import static clojure4j.core.Core.iterate;
import static clojure4j.core.Core.list;
import static clojure4j.core.Core.map;
import static clojure4j.core.Core.range;
import static clojure4j.core.Core.reduce;
import static clojure4j.core.Core.remove;
import static clojure4j.core.Core.rest;
import static clojure4j.core.Core.second;
import static clojure4j.core.Core.seq;
import static clojure4j.core.Core.sortedMap;
import static clojure4j.core.Core.sortedSet;
import static clojure4j.core.Core.take;
import static clojure4j.core.Core.vector;
import static clojure4j.core.Set.difference;
import static clojure4j.core.Set.intersection;
import static clojure4j.core.Set.select;
import static clojure4j.core.Set.union;
import static clojure4j.ext.Ext.entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure4j.ext.Ext;

public class CoreTest {

    @Test
    public void testBridgeComposition() {
        IFn plus = Clojure.var("clojure.core", "+");
        
        assertEquals(6L, Bridge.apply.invoke(plus, Bridge.list.invoke(1, 2, 3)));
    }
        
    private void testComposition(IPersistentCollection<Integer> oneTwoThree) {        
        // add all the numbers together
        int x = oneTwoThree.reduce(Core::add);
        assertEquals(6, x);
        
        // double the numbers and add them all
        assertEquals((Object) 12,
                oneTwoThree.map(q -> q * 2)
                           .reduce((z, y) -> z + y));
        
        // take the odd numbers, double them, and then add them all
//        assertEquals((Object) 8,
//                oneTwoThree.filter(x -> x % 2 == 1)
//                           .map(x -> x * 2)
//                           .reduce((x, y) -> x + y));
    }

    @Test
    public void testCompositionForList() {
        testComposition(list(1, 2, 3));
    }
    
    @Test
    public void testCompositionForVector() {
        testComposition(vector(1, 2, 3));
    }
    
    @Test
    public void testCompositionForHashSet() {
        testComposition(hashSet(1, 2, 3));
    }
    
    @Test
    public void testCompositionForSortedSet() {
        testComposition(sortedSet(1, 2, 3));
    }
    
    private static long addNumLong(Number a,  Long b) {
        return a.longValue() + b.longValue();
    }
    
    private static long addNumNum(Number a,  Number b) {
        return a.longValue() + b.longValue();
    }
    
//    private static BinaryFn<Number, Long, Long> addNumLongLambda = (a, b) -> a.longValue() + b.longValue();
    private static BinaryFn<Number, Number, Long> addNumNumLambda = (a, b) -> a.longValue() + b.longValue();

    @Test
    public void testGenericTypes() {
//        long l = list(1L, 2L, 3L, 4L).apply(CoreTest::addNumLong);
//        assertEquals(10L, l);
        
//        l = apply(CoreTest::addNumNum, list(1, 2L, 3, 4.0));
//        assertEquals(10L, l);

//        l = list(1L, 2L, 3L, 4L).apply(addNumLongLambda);
//        assertEquals(10L, l);
//        l = (new PersistentList<Number>(1L, 2L, 3L, 4L)).apply(addNumLongLambda);
//        assertEquals(10L, l);

//        l = apply(addNumNumLambda, list(1, 2L, 3, 4.0));
//        assertEquals(10L, l);
//        
//        IPersistentList<Number> numList = new PersistentList<>(1, 2L, 3, 4.0);
//        l = numList.conj(5.0).apply(addNumNumLambda);
//        assertEquals(15L, l);
        
//        assertEquals(PersistentList.class, conj(numList, 0.5).getClass());
//        assertEquals(PersistentList.class, numList.conj(5.0).getClass());        
    }
    
    @Test
    public void testHashMap() {
        Associative<Integer, String> map = new PersistentHashMap<>();
        
        map = map.assoc(1, "a").assoc(2, "b").assoc(3, "c");
        assertEquals("a", map.get(1));
        assertEquals("c", map.get(3));
        assertEquals("b", map.get(2));
        
        map = map.dissoc(2);
        assertNull(map.get(2));
    }
    
    @Test 
    public void testVector() {
        assertEquals(new Integer(2), get(vector(1, 2, 3), 1));
    }
    
    @Test 
    public void testAtom() {
        Atom<Integer> intAtom = new Atom<>(0);
        Atom<IPersistentMap<String, Integer>> mapAtom = new Atom<>(new PersistentHashMap<String, Integer>());
        
        assertEquals(new Integer(2), intAtom.swap(x -> x + 2));
        assertEquals(new Integer(2), intAtom.deref());
        assertEquals(new Integer(6), intAtom.swap(x -> x * 3));
        assertEquals(new Integer(6), intAtom.deref());
        assertEquals(new Integer(13), intAtom.reset(13));
        assertEquals(new Integer(13), intAtom.deref());
        
        
        mapAtom.swap(Core::assoc, "one", 1);
        assertEquals(new Integer(1), mapAtom.deref().get("one"));
        mapAtom.swap(Core::assoc, "two", 2);
        assertEquals(new Integer(1), mapAtom.deref().get("one"));
        assertEquals(new Integer(2), mapAtom.deref().get("two"));
        mapAtom.reset(new PersistentHashMap<>());
        assertNull(mapAtom.deref().get("one"));
        assertNull(mapAtom.deref().get("two"));
    }
    
    private void testSet(
            IPersistentSet<Integer> emptyInt, 
            IPersistentSet<String> emptyString,
            IPersistentSet<IPersistentMap<String, String>> emptyMaps) {
        
        IPersistentSet<Integer> set1234 = emptyInt.conj(1).conj(2).conj(3).conj(4).conj(2);
        IPersistentSet<String> animals = emptyString.conj("dog").conj("cat").conj("bat").conj("rat");
        IPersistentSet<String> animals2 = emptyString.conj("owl").conj("fox").conj("elk").conj("frog");
        
        // count & contains
        assertEquals(4, count(set1234));
        assertTrue(contains(set1234, 2));
        assertFalse(contains(set1234, 5));
        assertEquals(4, set1234.count());
        assertTrue(set1234.contains(2));
        assertFalse(set1234.contains(5));
        
        // conj & disj
        assertTrue(contains(conj(set1234, 13), 13));
        assertTrue(contains(disj(set1234, 2), 4));
        assertFalse(contains(disj(set1234, 2), 2));
        assertEquals(4, count(disj(set1234, 7)));
        assertEquals(1, count(disj(set1234, 2, 3, 4)));
        assertTrue(contains(disj(set1234, 2, 3, 4), 1));        
        assertTrue(set1234.conj(5).contains(5));
        assertTrue(set1234.disj(2).contains(4));
        assertFalse(set1234.disj(2).contains(2));
        assertEquals(3, set1234.disj(2).count());
        assertEquals(1, set1234.disj(2,3,4).count());
        assertTrue(set1234.disj(2,3,4).contains(1));
        
        // select
        assertTrue(set1234.select(x -> x % 2 == 0).contains(4));
        assertTrue(contains(select(x -> x % 2 == 1, set1234), 1));
        assertFalse(animals.select(x -> x.endsWith("at")).contains("dog"));
        assertFalse(contains(select(x -> x.endsWith("at"), animals), "dog"));
        
        // union
        IPersistentSet<String> animalsSuper = union(animals, animals2);
        assertTrue(animalsSuper.contains("dog"));
        assertTrue(animalsSuper.contains("elk"));
        assertFalse(animalsSuper.contains("rock"));
        assertEquals(count(animals) + count(animals2), count(animalsSuper));
        
        // difference
        IPersistentSet<String> animalsMinus = difference(animalsSuper, hashSet("dog", "elk"));
        assertTrue(animalsMinus.contains("cat"));
        assertFalse(animalsMinus.contains("dog"));
        assertFalse(animalsMinus.contains("elk"));
        
        // intersection
        IPersistentSet<String> animalsInt = intersection(animalsMinus, animals2);
        assertTrue(animalsInt.contains("fox"));
        assertTrue(animalsInt.contains("owl"));
        assertTrue(animalsInt.contains("frog"));
        assertFalse(animalsInt.contains("dog"));
        assertFalse(animalsInt.contains("elk"));
        
        // subset
        assertTrue(animalsInt.isSubset(animalsSuper));
        assertTrue(animalsMinus.isSubset(animalsSuper));
        assertFalse(animalsSuper.isSubset(animalsInt));
        assertFalse(animalsSuper.isSubset(animalsMinus));
        assertFalse(animals.isSubset(animals2));
        assertFalse(animals2.isSubset(animals));
        assertFalse(animals2.isSubset(animalsInt));
        assertTrue(animalsInt.isSubset(animals2));
        
        // superset
        assertFalse(animalsInt.isSuperset(animalsSuper));
        assertFalse(animalsMinus.isSuperset(animalsSuper));
        assertTrue(animalsSuper.isSuperset(animalsInt));
        assertTrue(animalsSuper.isSuperset(animalsMinus));
        assertFalse(animals.isSuperset(animals2));
        assertFalse(animals2.isSuperset(animals));
        assertFalse(animals2.isSubset(animalsInt));
        assertFalse(animalsInt.isSuperset(animals2));
  
//        IPersistentSet<IPersistentMap<String, String>> root = new PersistentHashSet<>();
//        IPersistentSet<IPersistentMap<String, String>> livestock =
//                emptyMaps.
//                conj(hashMap("name", "betsy").assoc("owner", "brian").assoc("kind", "cow")).
//                conj(hashMap("name", "jake").assoc("owner", "brian").assoc("kind", "horse")).
//                conj(hashMap("name", "josie").assoc("owner", "dawn").assoc("kind", "cow"));
//        IPersistentSet<IPersistentMap<String, String>> kinds =
//                emptyMaps.
//                conj(hashMap("kind", "cow").assoc("personality", "stoic")).
//                conj(hashMap("kind", "horse").assoc("personality", "skittish"));
//        IPersistentSet<IPersistentMap<String, String>> species =
//                emptyMaps.
//                conj(hashMap("species", "cow").assoc("personality", "stoic")).
//                conj(hashMap("species", "horse").assoc("personality", "skittish"));

        
        // TODO Figure out types for join and project
        // join
//        root = Set.join(livestock, kinds);
//        String personality = Core.first(filter(x -> x.get("name").equals("betsy"), root)).get("personality");
//        assertEquals("stoic", personality);
        
        // project
        //TODO
    }

    @Test
    public void testPersistentHashSet() {
        testSet(new PersistentHashSet<>(),
                new PersistentHashSet<>(),
                new PersistentHashSet<>());
    }
    
    @Test
    public void testPersistentSortedSet() {
        testSet(new PersistentSortedSet<>(),
                new PersistentSortedSet<>(),
                new PersistentSortedSet<>());        
    }
    
    @Test
    public void testComparableFns() {
//        assertFalse(list("a", "b", "c").apply(Ext::gt));
        assertFalse(Ext.gt("a", "b", "c"));
        assertTrue(Ext.gt("c", "b", "a"));
        
//        IPersistentList<String> l = list("c", "b", "a");
//        assertTrue(l.apply(Ext::gt));
    }
    
    @Test 
    public void testNumberFns() {
//        (map + [1 2 3] [4 5 6])
//        => '(5 7 9)
        
//        Core.map(Core::add, vector(1,2,3), 1);
        ISeq<Integer> intSeq = map(Core::add, vector(1,2,3), vector(4,5,6));
        assertTrue(vector(5, 7, 9).seq().equals(intSeq));
//        Core.map(Core::add, vector(1,2,3), vector(4,5,6), vector(7,8,9));
        
        //(map + [1 2 3] (iterate inc 1))
        map(Core::add, vector(1,2,3), iterate(Core::inc, 1));        
    }
    
    @Test
    public void testNullAndEmpty() {
        assertNull(vector().seq());
        assertNull(vector(1).rest().seq());
        assertNull(seq(null));
        
        assertNull(vector().first());
        assertNotNull(vector().rest());
        assertTrue(vector().rest() instanceof ISeq<?>);
        assertNull(rest(null));
        assertNull(first((IPersistentCollection<Object>) null));
        assertNull(first((IMapEntry<Object,Object>) null));
        assertNull(second((IPersistentCollection<Object>) null));
        assertNull(second((IMapEntry<Object,Object>) null));
        
        assertEquals(0, count(null));
    }
    
    // TODO Fix apply.  Definitely not right.  impl'd like reduce at the moment
    @Test
    public void testApply() {
        // (apply > [4 3 2 1) vs (reduce > [4 3 2 1])
        
//        (apply hash-map [:a 5 :b 6])
//        ;= {:a 5, :b 6}
//        (reduce hash-map [:a 5 :b 6])
//        ;= {{{:a 5} :b} 6}
//        (apply (fn [x y] (> x y)) [3 2 1]) ;; wrong number args

        int x;
//        int x = apply(Core::max, vector(1,2,3));
//        assertEquals(3, x);
        x = reduce(Core::max, vector(1,2,3));
        assertEquals(3, x);
//        x = apply(Core::add, vector(1,2,3,4));
//        assertEquals(10, x);
        x = reduce(Core::add, vector(1,2,3,4));
        assertEquals(10, x);
        
        boolean isMaxToMin = apply(Ext::gt, vector(4, 3, 2, 1));
        assertTrue(isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector(4, 2, 3, 1));
        assertFalse(isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector("d", "c", "b", "a"));
        assertTrue(isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector("d", "b", "c", "a"));
        assertFalse(isMaxToMin);   
        
        assertEquals(vector(0,1,2,3,4), apply(Core::vector, range(5)));
        assertEquals(list(0,1,2,3,4), apply(Core::list, range(5)));
        assertEquals(hashSet(0,1,2,3,4), apply(Core::hashSet, range(5)));
        assertEquals(sortedSet(0,1,2,3,4), apply(Core::hashSet, range(5)));
    }
    
    @Test
    public void testReduce() {
        assertEquals(15, (int)reduce(Core::add, vector(1,2,3,4,5)));
        assertEquals(20, 5, (int)reduce(Core::add, vector(1,2,3,4,5)));
        assertEquals(list(5L,4L,3L,2L,1L), range(2,6).reduce((l, x) -> l.conj(x), list(1L)));
        
//                       (reduce
//                         (fn [primes number]
//                           (if (some zero? (map (partial mod number) primes))
//                             primes
//                             (conj primes number)))
//                         [2]
//                         (take 1000 (iterate inc 3)))

//        reduce(
//            (primes, number) -> {
//                if(some(Core::isZero, map(partial(Core::mod, number), primes))) {
//                    return primes;
//                }
//                else {
//                    return conj(primes, number);
//                }
//            },
//            vector(2),
//            take(1000, iterate(Core::inc, 3)));
    }
    
    @Test
    public void testMapFunction() {
        assertEquals(vector(2,3,4,5).seq(), map(Core::inc, list(1,2,3,4)));
        assertEquals(vector(5,7,9).seq(), map(Core::add, vector(1,2,3), vector(4,5,6)));
        assertEquals(vector(2,4,6).seq(), map(Core::add, vector(1,2,3), iterate(Core::inc, 1)));
        
        ISeq<IPersistentVector<String>> vecSeq = map(Core::vector, vector("a", "b"), vector("c", "d"));
        assertEquals(vector("a", "c"), first(vecSeq));
        assertEquals(vector("b", "d"), second(vecSeq));
        
        // TODO Fix it; see TypedFn applyTo
//        assertEquals(
//                vector(entry("a", 2), entry("b", 4), entry("c", 6)),
//                map(x -> vector(first(x), 2 * second(x)),
//                        hashMap(entry("a", 1), entry("b", 2), entry("c", 3))));
    }
    
    @Test
    public void testInto() {
//        (= (seq [1 3]) (keys (into {} [[1 2] [3 4]])))
//        (into [] {1 2 3 4})
//        (into () '(1 2 3))
//        (into [] '(1 2 3))
        
        IPersistentMap<Integer,Integer> emptyMap = new PersistentHashMap<>();
        IPersistentVector<Integer> emptyVec = new PersistentVector<>();
        IPersistentList<Integer> emptyList = new PersistentList<>();
        
        assertTrue(list(3,2,1).equals(Core.into(emptyList, list(1,2,3))));
        assertTrue(vector(1,2,3).equals(Core.into(emptyVec, list(1,2,3))));
        
        //REVIEW Support vectors as map entries?  heterogeneous? i.e. IPersistentVector<Object>
        assertTrue(vector(1,3).seq().equals(Core.keys(Core.into(emptyMap, vector(entry(1,2), entry(3,4))))));

    }
    
    @Test
    public void testEquality() {
        assertNotSame(list(1,2,3), list(1,2,3));
        assertEquals(list(1,2,3), list(1,2,3));

        assertNotSame(vector("a", "b", "c"), vector("a", "b", "c"));
        assertEquals(vector("a", "b", "c"), vector("a", "b", "c"));
        
        assertNotSame(
                hashMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)), 
                hashMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)));
        assertEquals(
                hashMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)), 
                hashMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)));
        
        assertNotSame(
                sortedMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)), 
                sortedMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)));
        assertEquals(
                sortedMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)), 
                sortedMap(entry("weight", 180.5), entry("age", 21), entry("height", 1.8)));
        
        assertNotSame(hashSet('c','b','a'), hashSet('c','b','a'));
        assertEquals(hashSet('c','b','a'), hashSet('c','b','a'));
        
        assertNotSame(sortedSet('c','b','a'), sortedSet('c','b','a'));
        assertEquals(sortedSet('c','b','a'), sortedSet('c','b','a'));
        
        assertNotSame(vector(1,2,3), vector(1,2,3));
        assertEquals(vector(1,2,3), vector(1,2,3));
        
        assertEquals(vector(1,2,3).seq(), list(1,2,3).seq());
        assertEquals(list(1,2,3).seq(), sortedSet(1,2,3).seq());
        assertEquals(vector(1,2,3).seq(), sortedSet(1,2,3).seq());
    }
    
    @Test
    public void testPredicates() {
        assertTrue(isZero(0));
        assertTrue(isZero(0L));
        assertTrue(isZero(0f));
        assertTrue(isZero(0.0));
        assertFalse(isZero(-1));
        assertFalse(isZero(-1L));
        assertFalse(isZero(-1f));
        assertFalse(isZero(-1.1));
        assertFalse(isZero(10));
        assertFalse(isZero(10L));
        assertFalse(isZero(10f));
        assertFalse(isZero(10.0));
        assertEquals(vector(1, 2, 3), apply(Core::vector, remove(Core::isZero, vector(1,0,2,0,3))));
        
        assertTrue(Core.isSome(x -> x > 3, hashSet(1,2,3,4,5)));
        assertTrue(Core.isSome(Core::isEven, range(4)));
        assertFalse(Core.isSome(Core::isEven, range(1, 8, 2)));
    }
    
    @Test
    public void testLazyInfinite() {
        ISeq<Integer> posInts = iterate(Core::inc, 0);
        assertEquals(new Integer(0), posInts.first());
        assertEquals(new Integer(2), posInts.rest().rest().first());
        assertEquals(vector(0,1,2,3,4), apply(Core::vector, take(5, iterate(Core::inc, 0))));
    }
    
    @Test 
    public void testMisc() {
        assertEquals(1, inc(0));
        assertEquals(0, inc(-1));
        assertEquals(-2, dec(-1));
        assertEquals(-1, dec(0));
        assertEquals(0, dec(1));
    }
    
    @Test
    public void testLambdasOnInternal() {
        // TODO Breaks JVM?  Eclipse bug? Identify problem
//        assertEquals(2, second(map(x -> get(x, 1L), list(vector(1,2,3)))));
    }
    
    
}
