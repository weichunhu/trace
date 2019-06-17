package com.trace.web.interceptor.registry;

import com.trace.web.interceptor.TraceWebInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Trace Web Interceptor Registry
 * @author chunhua.wei
 */
@Component
public class TraceWebInterceptorRegistry extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceWebInterceptor());
    }

}