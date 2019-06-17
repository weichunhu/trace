package com.trace.context;


import com.trace.context.generator.TraceIdGenerator;

/**
 * TraceId context
 * @author 364623395@qq.com
 */
public class TraceIdContext {
    private static final InheritableThreadLocal<String> TRACE_ID_CONTEXT = new InheritableThreadLocal<String>();

    public static String traceId(){
        return TRACE_ID_CONTEXT.get();
    }

    public static void initTraceId(String traceId){
        if(TRACE_ID_CONTEXT.get() == null){
            traceId = TraceIdGenerator.generateTraceId();
        }

        TRACE_ID_CONTEXT.set(traceId);
    }

}