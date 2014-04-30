package clojure4j.core;

import static clojure4j.core.Core.apply;
import static clojure4j.core.Core.conj;
import static clojure4j.core.Core.contains;
import static clojure4j.core.Core.count;
import static clojure4j.core.Core.disj;
import static clojure4j.core.Core.first;
import static clojure4j.core.Core.hashSet;
import static clojure4j.core.Core.iterate;
import static clojure4j.core.Core.list;
import static clojure4j.core.Core.map;
import static clojure4j.core.Core.reduce;
import static clojure4j.core.Core.rest;
import static clojure4j.core.Core.seq;
import static clojure4j.core.Core.sortedSet;
import static clojure4j.core.Core.vector;
import static clojure4j.core.Ext.entry;
import static clojure4j.core.Set.difference;
import static clojure4j.core.Set.intersection;
import static clojure4j.core.Set.select;
import static clojure4j.core.Set.union;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class CoreTest {

    @Test
    public void testBridgeComposition() {
        IFn plus = Clojure.var("clojure.core", "+");
        
        assertEquals(6L, Bridge.apply.invoke(plus, Bridge.list.invoke(1, 2, 3)));
    }
        
    private void testComposition(IPersistentCollection<Integer> oneTwoThree) {        
        // add all the numbers together
        assertEquals(6, (int) oneTwoThree.apply((x, y) -> x + y));
        
        // double the numbers and add them all
        assertEquals((Object) 12,
                oneTwoThree.map(x -> x * 2)
                           .apply((x, y) -> x + y));
        
        // take the odd numbers, double them, and then add them all
        assertEquals((Object) 8,
                oneTwoThree.filter(x -> x % 2 == 1)
                           .map(x -> x * 2)
                           .apply((x, y) -> x + y));
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
        long l = list(1L, 2L, 3L, 4L).apply(CoreTest::addNumLong);
        assertEquals(10L, l);
        
        l = apply(CoreTest::addNumNum, list(1, 2L, 3, 4.0));
        assertEquals(10L, l);

//        l = list(1L, 2L, 3L, 4L).apply(addNumLongLambda);
//        assertEquals(10L, l);
//        l = (new PersistentList<Number>(1L, 2L, 3L, 4L)).apply(addNumLongLambda);
//        assertEquals(10L, l);

        l = apply(addNumNumLambda, list(1, 2L, 3, 4.0));
        assertEquals(10L, l);
        
        IPersistentList<Number> numList = new PersistentList<>(1, 2L, 3, 4.0);
        l = numList.conj(5.0).apply(addNumNumLambda);
        assertEquals(15L, l);
        
        assertEquals(PersistentList.class, conj(numList, 0.5).getClass());
        assertEquals(PersistentList.class, numList.conj(5.0).getClass());        
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
        assertEquals(true, contains(set1234, 2));
        assertEquals(false, contains(set1234, 5));
        assertEquals(4, set1234.count());
        assertEquals(true, set1234.contains(2));
        assertEquals(false, set1234.contains(5));
        
        // conj & disj
        assertEquals(true, contains(conj(set1234, 13), 13));
        assertEquals(true, contains(disj(set1234, 2), 4));
        assertEquals(false, contains(disj(set1234, 2), 2));
        assertEquals(4, count(disj(set1234, 7)));
        assertEquals(1, count(disj(set1234, 2, 3, 4)));
        assertEquals(true, contains(disj(set1234, 2, 3, 4), 1));        
        assertEquals(true, set1234.conj(5).contains(5));
        assertEquals(true, set1234.disj(2).contains(4));
        assertEquals(false, set1234.disj(2).contains(2));
        assertEquals(3, set1234.disj(2).count());
        assertEquals(1, set1234.disj(2,3,4).count());
        assertEquals(true, set1234.disj(2,3,4).contains(1));
        
        // select
        assertEquals(true, set1234.select(x -> x % 2 == 0).contains(4));
        assertEquals(true, contains(select(x -> x % 2 == 1, set1234), 1));
        assertEquals(false, animals.select(x -> x.endsWith("at")).contains("dog"));
        assertEquals(false, contains(select(x -> x.endsWith("at"), animals), "dog"));
        
        // union
        IPersistentSet<String> animalsSuper = union(animals, animals2);
        assertEquals(true, animalsSuper.contains("dog"));
        assertEquals(true, animalsSuper.contains("elk"));
        assertEquals(false, animalsSuper.contains("rock"));
        assertEquals(count(animals) + count(animals2), count(animalsSuper));
        
        // difference
        IPersistentSet<String> animalsMinus = difference(animalsSuper, hashSet("dog", "elk"));
        assertEquals(true, animalsMinus.contains("cat"));
        assertEquals(false, animalsMinus.contains("dog"));
        assertEquals(false, animalsMinus.contains("elk"));
        
        // intersection
        IPersistentSet<String> animalsInt = intersection(animalsMinus, animals2);
        assertEquals(true, animalsInt.contains("fox"));
        assertEquals(true, animalsInt.contains("owl"));
        assertEquals(true, animalsInt.contains("frog"));
        assertEquals(false, animalsInt.contains("dog"));
        assertEquals(false, animalsInt.contains("elk"));
        
        // subset
        assertEquals(true, animalsInt.isSubset(animalsSuper));
        assertEquals(true, animalsMinus.isSubset(animalsSuper));
        assertEquals(false, animalsSuper.isSubset(animalsInt));
        assertEquals(false, animalsSuper.isSubset(animalsMinus));
        assertEquals(false, animals.isSubset(animals2));
        assertEquals(false, animals2.isSubset(animals));
        assertEquals(false, animals2.isSubset(animalsInt));
        assertEquals(true, animalsInt.isSubset(animals2));
        
        // superset
        assertEquals(false, animalsInt.isSuperset(animalsSuper));
        assertEquals(false, animalsMinus.isSuperset(animalsSuper));
        assertEquals(true, animalsSuper.isSuperset(animalsInt));
        assertEquals(true, animalsSuper.isSuperset(animalsMinus));
        assertEquals(false, animals.isSuperset(animals2));
        assertEquals(false, animals2.isSuperset(animals));
        assertEquals(false, animals2.isSubset(animalsInt));
        assertEquals(false, animalsInt.isSuperset(animals2));
  
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
//        assertEquals(false, list("a", "b", "c").apply(Ext::gt));
        assertEquals(false, Ext.gt("a", "b", "c"));
        assertEquals(true, Ext.gt("c", "b", "a"));
        assertEquals(true, Ext.gt(list("c", "b", "a")));
        
//        IPersistentList<String> l = list("c", "b", "a");
//        assertEquals(true, l.apply(Ext::gt));
    }
    
    @Test 
    public void testNumberFns() {
//        (map + [1 2 3] [4 5 6])
//        => '(5 7 9)
        
//        Core.map(Core::add, vector(1,2,3), 1);
        ISeq<Integer> intSeq = map(Core::add, vector(1,2,3), vector(4,5,6));
        assertEquals(true, vector(5, 7, 9).seq().equals(intSeq));
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
        assertEquals(true, vector().rest() != null);
        assertEquals(true, vector().rest() instanceof ISeq<?>);
        assertNull(rest(null));
        assertNull(first(null));
        
        assertEquals(0, count(null));
    }
    
    // TODO Fix apply.  Definitely not right.  impl'd like reduce at the moment
    @Test
    public void testApplyVsReduce() {
        // (apply > [4 3 2 1) vs (reduce > [4 3 2 1])
        
//        (apply hash-map [:a 5 :b 6])
//        ;= {:a 5, :b 6}
//        (reduce hash-map [:a 5 :b 6])
//        ;= {{{:a 5} :b} 6}
//        (apply (fn [x y] (> x y)) [3 2 1]) ;; wrong number args
        
        int x = apply(Core::max, vector(1,2,3));
        assertEquals(3, x);
        x = reduce(Core::max, vector(1,2,3));
        assertEquals(3, x);
        x = apply(Core::add, vector(1,2,3,4));
        assertEquals(10, x);
        x = reduce(Core::add, vector(1,2,3,4));
        assertEquals(10, x);
        
        boolean isMaxToMin = apply(Ext::gt, vector(4, 3, 2, 1));
        assertEquals(true, isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector(4, 2, 3, 1));
        assertEquals(false, isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector("d", "c", "b", "a"));
        assertEquals(true, isMaxToMin);
        isMaxToMin = apply(Ext::gt, vector("d", "b", "c", "a"));
        assertEquals(false, isMaxToMin);

        
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
        
        assertEquals(true, list(3,2,1).equals(Core.into(emptyList, list(1,2,3))));
        assertEquals(true, vector(1,2,3).equals(Core.into(emptyVec, list(1,2,3))));
        
        //REVIEW Support vectors as map entries?  heterogeneous? i.e. IPersistentVector<Object>
        assertEquals(true, vector(1,3).seq().equals(Core.keys(Core.into(emptyMap, vector(entry(1,2), entry(3,4))))));

    }
}
