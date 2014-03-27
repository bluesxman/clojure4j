package clojure4j.core;

import static org.junit.Assert.*;

import org.junit.Test;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class CoreTest {

    @Test
    public void testBridgeComposition() {
        IFn plus = Clojure.var("clojure.core", "+");
        
        assertEquals(6L, Bridge.apply.invoke(plus, Bridge.list.invoke(1, 2, 3)));
    }
    
    @Test
    public void testListComposition(){
        PersistentList<Integer> list123 = new PersistentList<Integer>(1,2,3);
        assertEquals(6, list123.apply((x, y) -> x + y));        
    }

}
