package clojure4j.core;

import static clojure4j.core.Core.apply;
import static clojure4j.core.Core.conj;
import static clojure4j.core.Core.hashSet;
import static clojure4j.core.Core.list;
import static clojure4j.core.Core.sortedSet;
import static clojure4j.core.Core.vector;
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

}
