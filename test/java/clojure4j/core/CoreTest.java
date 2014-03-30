package clojure4j.core;

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
        assertEquals(12, 
                oneTwoThree.map(x -> x * 2)
                           .apply((x, y) -> x + y));
        
        // take the odd numbers, double them, and then add them all
        assertEquals(8, 
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
