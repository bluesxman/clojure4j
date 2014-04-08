package clojure4j.core;

import static clojure4j.core.Core.hashMap;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class Performance {
    int seed = 87;

    public Performance() {
        // TODO Auto-generated constructor stub
        
        // baseline, 
    }

    public IPersistentMap<Object, Object> measure(Runnable fn, int iterations, int warmup) {
        long start, stop;
        
        for(int i = 0; i < warmup; i++) {
            fn.run();
        }
        
        start = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            fn.run();
        }
        stop = System.nanoTime();
        
        return hashMap().assoc(":run-time", ((stop - start) / 1e9));
    }
    
    @Test
    public void testMapAdd() {
        int size = 100000;
        int iterations = 100;
        int warmup = 20;
        Runnable fn;

        fn = () -> {
            int randInt;
            Map<Integer, Integer> util = new HashMap<Integer, Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.put(randInt, randInt);
            }            
        };
        System.out.println("java.util.HashMap: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            Map<Integer, Integer> util = new HashMap<Integer, Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.put(randInt, randInt);
            }
        };
        System.out.println("java.util.HashMap: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            clojure.lang.IPersistentMap clj = clojure.lang.PersistentHashMap.EMPTY;
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                clj = clj.assoc(randInt, randInt);
            }
        };        
        System.out.println("clojure.lang.PersistentHashMap: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            IPersistentMap<Integer, Integer> pds = new PersistentHashMap<Integer, Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                pds = pds.assoc(randInt, randInt);
            }
        };        
        System.out.println("clojure4j.core.PersistentHashMap: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            List<Integer> util = new ArrayList<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.add(randInt);
            }
        };
        System.out.println("java.util.ArrayList: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            clojure.lang.IPersistentVector clj = clojure.lang.PersistentVector.EMPTY;
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                clj = (clojure.lang.IPersistentVector) Bridge.conj.invoke(clj, randInt);
            }
        };        
        System.out.println("clojure.lang.PersistentVector: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            IPersistentVector<Integer> pds = new PersistentVector<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                pds = pds.conj(randInt);
            }
        };        
        System.out.println("clojure4j.core.PersistentVector: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            List<Integer> util = new LinkedList<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.add(randInt);
            }
        };
        System.out.println("java.util.LinkedList: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            clojure.lang.IPersistentList clj = clojure.lang.PersistentList.EMPTY;
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                clj = (clojure.lang.IPersistentList) Bridge.conj.invoke(clj, randInt);
            }
        };        
        System.out.println("clojure.lang.PersistentList: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            IPersistentList<Integer> pds = new PersistentList<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                pds = pds.conj(randInt);
            }
        };        
        System.out.println("clojure4j.core.PersistentList: " + measure(fn, iterations, warmup).get(":run-time"));            

        fn = () -> {
            int randInt;
            Set<Integer> util = new HashSet<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                util.add(randInt);
            }
        };
        System.out.println("java.util.HashSet: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            clojure.lang.IPersistentSet clj = clojure.lang.PersistentHashSet.EMPTY;
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                clj = (clojure.lang.IPersistentSet) Bridge.conj.invoke(clj, randInt);
            }
        };        
        System.out.println("clojure.lang.PersistentSet: " + measure(fn, iterations, warmup).get(":run-time"));

        fn = () -> {
            int randInt;
            IPersistentSet<Integer> pds = new PersistentHashSet<Integer>();
            Random rand = new Random(seed);

            for(int i = 0; i < size; i++) {
                randInt = rand.nextInt();
                pds = pds.conj(randInt);
            }
        };        
        System.out.println("clojure4j.core.PersistentHashSet: " + measure(fn, iterations, warmup).get(":run-time"));            



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
