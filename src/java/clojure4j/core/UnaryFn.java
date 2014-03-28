package clojure4j.core;

import java.util.function.Function;

import clojure.lang.IFn;
import clojure.lang.ISeq;

public interface UnaryFn <T, R> extends Function<T, R>, IFn {

    // Callable
    @Override
    default R call() {
        throw new UnsupportedOperationException();
    }

    // Runnable
    @Override
    default void run() {
        throw new UnsupportedOperationException();        
    }

    // IFn
    @Override
    default Object invoke() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    @Override
    default Object invoke(Object arg1) {
        return apply((T) arg1);
    }

    @Override
    default Object invoke(Object arg1, Object arg2) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16, Object arg17) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16, Object arg17, Object arg18) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16, Object arg17, Object arg18, Object arg19) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16, Object arg17, Object arg18, Object arg19, Object arg20)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    default Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7,
            Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13, Object arg14,
            Object arg15, Object arg16, Object arg17, Object arg18, Object arg19, Object arg20,
            Object... args)
    {
        throw new UnsupportedOperationException();
    }

    @Override
//    default Object applyTo(ISeq arglist) {
//        return AFn.applyToHelper(this, Util.ret1(arglist,arglist = null));
//    }
    default Object applyTo(ISeq arglist) {
        ISeq seq = arglist;
        Object accum = arglist.first();
        
        while((seq = seq.next()) != null) {
            accum = invoke(accum, seq.first());
        }
        
        return accum;
    }
}
