package com.trace.context;

import com.trace.common.constant.trace.TraceConstants;
import com.trace.context.pid.PidUtil;
import com.trace.model.Trace;
import org.slf4j.MDC;

/**
 * Trace Context
 * @author 364623395@qq.com
 */
public class TraceContext {
    /**
     * Trace Context
     */
    private static final InheritableThreadLocal<Trace> TRACE_CONTEXT = new InheritableThreadLocal<Trace>();

    public static Trace trace(){
        Trace trace = TRACE_CONTEXT.get();
        MDC.put(TraceConstants.TRACE_ID, trace.getTraceId());
        MDC.put(TraceConstants.SPAN_ID, trace.getSpanId());
        return trace;
    }

    public static void initTraceContext(){
        Trace trace = TRACE_CONTEXT.get();
        if(trace == null){
            trace = new Trace.Builder()
                             .traceId(TraceIdContext.traceId())
                             .spanId(TraceConstants.ROOT_SPAN_ID)
                             .pid(PidUtil.getPid())
                             .build();

            TRACE_CONTEXT.set(trace);
        }
    }

    public static void trace(Trace trace){
        if(trace != null){
            TRACE_CONTEXT.set(trace);
        }
    }

    public static void remove(){
        TRACE_CONTEXT.remove();
    }

}