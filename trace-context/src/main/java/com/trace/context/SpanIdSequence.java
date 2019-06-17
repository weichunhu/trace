package com.trace.context;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * spanId SEQUENCE
 * @author chunhua.wei
 */
public class SpanIdSequence {
    private static final InheritableThreadLocal<AtomicInteger> SPAN_ID_SEQUENCE = new InheritableThreadLocal<AtomicInteger>(){
        @Override
        protected AtomicInteger initialValue() {
            return new AtomicInteger(0);
        }
    };

    public static Integer generateSequence(){
        return SPAN_ID_SEQUENCE.get().addAndGet(1);
    }

}