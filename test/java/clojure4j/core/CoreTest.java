package clojure4j.core;

import static clojure4j.core.Core.apply;
import static clojure4j.core.Core.conj;
import static clojure4j.core.Core.contains;
import static clojure4j.core.Core.count;
import static clojure4j.core.Core.disj;
import static clojure4j.core.Core.hashMap;
import static clojure4j.core.Core.hashSet;
import static clojure4j.core.Core.list;
import static clojure4j.core.Core.sortedSet;
import static clojure4j.core.Core.vector;
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
        assertEquals(6, oneTwoThree.apply((x, y) -> x + y));
        
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
        
        IPersistentList<Number> numList = new PersistentList<Number>(1, 2L, 3, 4.0);
        l = numList.conj(5.0).apply(addNumNumLambda);
        assertEquals(15L, l);
        
        assertEquals(PersistentList.class, conj(numList, 0.5).getClass());
        assertEquals(PersistentList.class, numList.conj(5.0).getClass());        
    }
    
    @Test
    public void testHashMap() {
        Associative<Integer, String> map = new PersistentHashMap<Integer, String>();
        
        map = map.assoc(1, "a").assoc(2, "b").assoc(3, "c");
        assertEquals("a", map.get(1));
        assertEquals("c", map.get(3));
        assertEquals("b", map.get(2));
        
        map = map.dissoc(2);
        assertNull(map.get(2));
    }
    
    @Test 
    public void testAtom() {
        Atom<Integer> intAtom = new Atom<Integer>(0);
        Atom<IPersistentMap<String, Integer>> mapAtom = 
                new Atom<IPersistentMap<String, Integer>>(new PersistentHashMap<String, Integer>());
        
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
        mapAtom.reset(new PersistentHashMap<String, Integer>());
        assertNull(mapAtom.deref().get("one"));
        assertNull(mapAtom.deref().get("two"));
    }
    
    private void testSet(
            IPersistentSet<Integer> emptyInt, 
            IPersistentSet<String> emptyString,
            IPersistentSet<IPersistentMap<? extends Object, ? extends Object>> emptyMaps) {
        
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
  
        // REVIEW Should API take ? extends Object?  Similar to Brian's issue earlier
//        IPersistentSet<IPersistentMap<? extends Object, ? extends Object>> livestock =
//                emptyMaps.
//                conj(hashMap("name", "betsy").assoc("owner", "brian").assoc("kind", "cow")).
//                conj(hashMap("name", "betsy").assoc("owner", "brian").assoc("kind", "cow")).
//                conj(hashMap("name", "betsy").assoc("owner", "brian").assoc("kind", "cow"));
//        IPersistentSet<IPersistentMap<? extends Object, ? extends Object>> kinds =
//                emptyMaps.
//                conj(hashMap("kind", "cow").assoc("personality", "stoic")).
//                conj(hashMap("kind", "horse").assoc("personality", "skittish"));
//        IPersistentSet<IPersistentMap<? extends Object, ? extends Object>> species =
//                emptyMaps.
//                conj(hashMap("species", "cow").assoc("personality", "stoic")).
//                conj(hashMap("species", "horse").assoc("personality", "skittish"));
//

        
        // TODO Figure out types for join and project
        // join
//        Set.join(livestock, kinds);
        
        // project
    }

    @Test
    public void testPersistentHashSet() {
        testSet(new PersistentHashSet<Integer>(),
                new PersistentHashSet<String>(),
                new PersistentHashSet<IPersistentMap<? extends Object, ? extends Object>>());
    }
    
    @Test
    public void testPersistentSortedSet() {
        testSet(new PersistentSortedSet<Integer>(),
                new PersistentSortedSet<String>(),
                new PersistentSortedSet<IPersistentMap<? extends Object, ? extends Object>>());        
    }
}
