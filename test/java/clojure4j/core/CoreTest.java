package clojure4j.core;

import static org.junit.Assert.*;

import org.junit.Test;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class CoreTest {

    @Test
    public void testCoreComposition() {
        IFn plus = Clojure.var("clojure.core", "+");
        
        assertEquals(6L, Core.apply.apply(plus, Core.list.invoke(1, 2, 3)));
    }
    
    @Test
    public void testListComposition(){
        IFn plus = Clojure.var("clojure.core", "+");
        PersistentList list123 = new PersistentList(1,2,3);
        assertEquals(6L, list123.apply(plus));        
    }

}
