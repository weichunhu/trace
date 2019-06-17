package com.trace.context.generator;

import com.trace.common.Constatns;

import java.util.UUID;

/**
 * Trace Id Generator
 * @author 364623395@qq.com
 */
public class TraceIdGenerator {

    public static final String generateTraceId(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace(Constatns.DASH, Constatns.EMPTY);
    }

}