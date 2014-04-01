package clojure4j.core;

import java.util.function.Supplier;

public interface NullaryFn<R> extends Supplier<R>, TypedFn<R> {

    @Override
    default R call(){
        return get();
    }
    
    @Override
    default Object invoke() {
        return get();
    }
}
