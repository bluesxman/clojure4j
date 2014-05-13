//package clojure4j.experiment;
//
//import org.junit.Test;
//
//import clojure4j.core.BinaryFn;
//import clojure4j.core.Core;
//import clojure4j.core.IPersistentCollection;
//
//public class LambdaExperiments {
//
//    interface VariadicFn<T,R> {
//        @SuppressWarnings("unchecked")
//        R apply(T... args);
//    }
//    
//    static final int add(int x, int y) {
//        return x + y;
//    }
//    
//    static final int add(int... xs) {
//        int accum = 0;
//        for(int x : xs) {
//            accum += x;
//        }
//        return accum;
//    }
//    
//    static final boolean gt(int x, int y) {
//        return x > y;
//    }
//    
//    static final boolean gt(int... xs) {
//        boolean accum = true;
//        int i = 0;
//        
//        while(accum && i < xs.length - 1) {
//            accum = accum && gt(xs[i], xs[i + 1]);
//        }
//        
//        return accum;
//    }
//    
//    @SuppressWarnings("unchecked")
//    static final <T,R> R apply(VariadicFn<T,R> fn, T... args){
//        return null;
//    }
//
//    static final <T,R> R apply(VariadicFn<T,R> fn, IPersistentCollection<T> col){
//        return null;
//    }
//    
//    @SafeVarargs
//    static final <T,R> R apply2(BinaryFn<T,T,T> fn, T... args){
//        return null;
//    }
//    
//    static final <T,R> R apply2(BinaryFn<T,T,T> fn, IPersistentCollection<T> col){
//        return null;
//    }
//    
//    @SuppressWarnings("unchecked")
//    static final <T,R> R apply3(VariadicFn<T,R> fn, T... args){
//        return null;
//    }
//    
//    static final <T,R> R apply3(VariadicFn<T,R> fn, IPersistentCollection<T> col){
//        return null;
//    }
//    
//    @SafeVarargs
//    static final <T,R> R apply3(BinaryFn<T,T,T> fn, T... args){
//        return null;
//    }
//    
//    static final <T,R> R apply3(BinaryFn<T,T,T> fn, IPersistentCollection<T> col){
//        return null;
//    }
//    
//    // For apply...
//    // 1) The # of args must match the arity of the fn being applied.  
//    // 2) Only a variadic fn can be applied to var args
//
//    @Test
//    public void testLambdas() {
//
//    }
//    
//    @Test
//    public void testAmbiguousLambda() {
//        apply((int... x) -> x[0], Core.vector(1,2,3,4));
//        
//    }
//}
