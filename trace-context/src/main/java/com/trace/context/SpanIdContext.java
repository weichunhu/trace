package com.trace.context;

import com.trace.common.constant.trace.TraceConstants;

/**
 * spanId Context
 * @author chunhua.wei
 */
public class SpanIdContext {
    private static final InheritableThreadLocal<String> SPAN_ID_CONTEXT = new InheritableThreadLocal<String>();

    public static String spanId(){
        String spanId = SPAN_ID_CONTEXT.get();

        // build spanId
        int sequence = SpanIdSequence.generateSequence();
        StringBuilder sb = new StringBuilder().append(spanId)
                                              .append(TraceConstants.DOT)
                                              .append(sequence);

        return sb.toString();
    }

    public static void initSpanId(String spanId){
        if(spanId == null || spanId.length() == 0){
            spanId = TraceConstants.ROOT_SPAN_ID;
        }

        SPAN_ID_CONTEXT.set(spanId);
    }

    public static String sourceSpanId(){
        return SPAN_ID_CONTEXT.get();
    }

}