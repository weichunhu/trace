package com.trace.dubbo.plugin.base;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.TimeoutException;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.trace.common.constant.logger.TraceLogger;
import com.trace.common.constant.protocol.TransportProtocol;
import com.trace.common.constant.state.TraceStatus;
import com.trace.common.constant.trace.TraceConstants;
import com.trace.context.SpanIdContext;
import com.trace.context.TraceContext;
import com.trace.context.TraceIdContext;
import com.trace.model.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Dubbo Trace Abstract Filter
 * @author chunhua.wei
 */
public abstract class AbstractTraceFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TraceLogger.TRACE_LOGGER);

    public Result doInvoke(Invoker<?> invoker, Invocation invocation) {
        RpcContext context = RpcContext.getContext();
        String traceId = context.getAttachment(TraceConstants.TRACE_ID);
        String spanId = context.getAttachment(TraceConstants.SPAN_ID);

        // init
        TraceIdContext.initTraceId(traceId);
        SpanIdContext.initSpanId(spanId);

        URL url = invoker.getUrl();
        StringBuilder sb = new StringBuilder().append(invoker.getInterface())
                                              .append(TraceConstants.DOT)
                                              .append(invocation.getMethodName());

        // build trace
        TraceContext.initTraceContext();
        Trace trace = TraceContext.trace();
        trace.setApplication(url.getParameter(Constants.APPLICATION_KEY));
        trace.setStartTime(System.currentTimeMillis());
        trace.setIp(url.getIp());
        trace.setPort(url.getPort());
        trace.setUri(sb.toString());
        if(StringUtils.hasText(spanId)){
            trace.setSpanId(SpanIdContext.spanId());
        }

        trace.setProtocol(TransportProtocol.DUBBO.name());
        Result result;

        try{
            context.setAttachment(TraceConstants.TRACE_ID, trace.getTraceId());
            context.setAttachment(TraceConstants.SPAN_ID, SpanIdContext.sourceSpanId());
            result = invoker.invoke(invocation);
            trace.setStatus(TraceStatus.OK.name());
        }catch(Throwable t){
            TraceStatus traceStatus = TraceStatus.ERROR;
            if(t instanceof TimeoutException){
                traceStatus = TraceStatus.TIME_OUT;
            }

            trace.setStatus(traceStatus.name());
            throw new RpcException(t);
        }finally{
            trace.setEndTime(System.currentTimeMillis());
            logger.info(JSON.toJSONString(trace));
        }

        return result;
    }

}