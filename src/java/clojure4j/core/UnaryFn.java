package clojure4j.core;

import java.util.function.Function;

import clojure.lang.IFn;

public interface UnaryFn<T, R> extends Function<T, R>, IFn {

}
