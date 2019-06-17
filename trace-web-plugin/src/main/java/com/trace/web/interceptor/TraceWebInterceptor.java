package com.trace.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.trace.common.constant.logger.TraceLogger;
import com.trace.common.constant.protocol.TransportProtocol;
import com.trace.common.constant.state.TraceStatus;
import com.trace.common.constant.trace.TraceConstants;
import com.trace.context.SpanIdContext;
import com.trace.context.TraceContext;
import com.trace.context.TraceIdContext;
import com.trace.model.Trace;
import com.trace.web.http.HttpHeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Trace Web Interceptor
 * @author chunhua.wei
 */
public class TraceWebInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(TraceLogger.TRACE_LOGGER);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = HttpHeaderUtil.fromHeader(TraceConstants.TRACE_ID, request);
        String spanId = HttpHeaderUtil.fromHeader(TraceConstants.SPAN_ID, request);

        // init
        TraceIdContext.initTraceId(traceId);
        SpanIdContext.initSpanId(spanId);
        TraceContext.initTraceContext();

        Trace trace = TraceContext.trace();
        trace.setStartTime(System.currentTimeMillis());
        trace.setUri(request.getRequestURI());
        if(StringUtils.hasText(spanId)){
            trace.setSpanId(SpanIdContext.spanId());
        }

        trace.setProtocol(TransportProtocol.HTTP.name());
        return Boolean.TRUE;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // do nothing
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Trace trace = TraceContext.trace();
        if(trace != null){
            trace.setEndTime(System.currentTimeMillis());

            // trace status
            TraceStatus status = TraceStatus.convert(response.getStatus());
            trace.setStatus(status.name());
            logger.info(JSON.toJSONString(trace));
        }
    }

}