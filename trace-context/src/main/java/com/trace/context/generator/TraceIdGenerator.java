package com.trace.context.generator;

import com.trace.common.Constatns;

import java.util.UUID;

/**
 * Trace Id Generator
 * @author chunhua.wei
 */
public class TraceIdGenerator {

    public static final String generateTraceId(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace(Constatns.DASH, Constatns.EMPTY);
    }

}