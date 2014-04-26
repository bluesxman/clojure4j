package clojure4j.core;

/**
 * This class contains fns that are neither part of clojure, the clojure ecosystem, nor any 3rd-party clojure libraries.
 */
public final class Ext {

    public static final <T extends Comparable<T>> boolean gt(T left, T right) {
        return left.compareTo(right) > 0;
    }

    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> boolean gt(T... comps){
        if(comps.length > 0) {
            boolean result = true;
            
            for(int i = 1; i < comps.length; i++) {
                result = result && gt(comps[i - 1], comps[i]);
            }
            
            return result;
        }
        else {
            throw new IllegalArgumentException("1 or more arguments expected");
        }
    }
    
    public static final <T extends Comparable<T>> boolean gt(IPersistentCollection<T> col){
        if(col.count() > 0) {
            boolean result = true;
            T hd;
            IPersistentCollection<T> tail = col;
            
            while(result && tail.count() > 1) {
                hd = tail.first();
                tail = tail.rest();
                result = result && gt(hd, tail.first());
            }
            
            return result;
        }
        else {
            throw new IllegalArgumentException("1 or more arguments expected");
        }
    }
    
    public static final <T extends Comparable<T>> boolean gteq(T left, T right) {
        return left.compareTo(right) >= 0;
    }

    public static final <T extends Comparable<T>> boolean lt(T left, T right) {
        return left.compareTo(right) < 0;
    }

    public static final <T extends Comparable<T>> boolean lteq(T left, T right) {
        return left.compareTo(right) <= 0;
    }
    
    public static final <T extends Comparable<T>> boolean eq(T left, T right) {
        return left.compareTo(right) <= 0;
    }
    
    public static final <T extends Comparable<T>> boolean noteq(T left, T right) {
        return left.compareTo(right) <= 0;
    }
    

}
