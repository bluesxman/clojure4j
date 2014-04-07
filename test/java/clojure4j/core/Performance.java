package clojure4j.core;

import static clojure4j.core.Core.hashMap;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class Performance {
    int seed = 87;

    public Performance() {
        // TODO Auto-generated constructor stub
    }

    public IPersistentMap<Object, Object> measure(Runnable fn) {
        fn.run();
        
        long start = System.nanoTime();
        fn.run();
        return hashMap().assoc(":run-time", ((System.nanoTime() - start) / 1e9));
    }
    
    @Test
    public void testMapAdd() {
        int size = 100000;
        Runnable fn;
        
        fn = () -> {
            int randInt;
            Map<Integer, String> util = new HashMap<Integer, String>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.put(randInt, String.valueOf(randInt));
            }            
        };
        System.out.println("java.util.HashMap: " + measure(fn).get(":run-time"));

        for(int j = 0; j < 10; j++) {

            fn = () -> {
                int randInt;
                IPersistentMap<Integer, String> pds = new PersistentHashMap<Integer, String>();
                Random rand = new Random(seed);

                for(int i = 0; i < size; i++) {
                    randInt = rand.nextInt();
                    pds = pds.assoc(randInt, String.valueOf(randInt));
                }
            };        
            System.out.println("clojure4j.core.PersistentHashMap: " + measure(fn).get(":run-time"));

            fn = () -> {
                int randInt;
                Map<Integer, String> util = new HashMap<Integer, String>();
                Random rand = new Random(seed);

                for(int i = 0; i < size; i++) {
                    randInt = rand.nextInt();
                    util.put(randInt, String.valueOf(randInt));
                }
            };
            System.out.println("java.util.HashMap: " + measure(fn).get(":run-time"));
            
            fn = () -> {
                int randInt;
                clojure.lang.IPersistentMap clj = clojure.lang.PersistentHashMap.EMPTY;
                Random rand = new Random(seed);

                for(int i = 0; i < size; i++) {
                    randInt = rand.nextInt();
                    clj = clj.assoc(randInt, String.valueOf(randInt));
                }
            };        
            System.out.println("clojure.lang.PersistentHashMap: " + measure(fn).get(":run-time"));

        }
        
//
//        for(int j = 0; j < 10; j++) {
//
//            fn = () -> {
//                int randInt;
//                IPersistentMap<String, Integer> pds = new PersistentHashMap<String, Integer>();
//                Random rand = new Random(seed);
//
//                for(int i = 0; i < size; i++) {
//                    randInt = rand.nextInt();
//                    pds = pds.assoc(String.valueOf(randInt), randInt);
//                }
//            };        
//            System.out.println("clojure4j.core.PersistentHashMap: " + measure(fn).get(":run-time"));
//
//            fn = () -> {
//                int randInt;
//                Map<String, Integer> util = new HashMap<String, Integer>();
//                Random rand = new Random(seed);
//
//                for(int i = 0; i < size; i++) {
//                    randInt = rand.nextInt();
//                    util.put(String.valueOf(randInt), randInt);
//                }
//            };
//            System.out.println("java.util.HashMap: " + measure(fn).get(":run-time"));
//        }

    }
    
    // foreach package [java.util, clojure.lang, clojure4j.core]
    // foreach data structure [list, vector, hashMap, hasSet, sortedMap, sortedSet]
    // foreach function [add, remove, size, get, hashCode]
    // foreach element type [char, byte, short, int, long, float, double, String, Object]
}
