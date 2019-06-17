package com.trace.common.constant.state;

/**
 * Trace Status
 * @author chunhua.wei
 */
public enum TraceStatus {
    /**
     * 请求失败
     */
    ERROR(100, "请求失败"),

    /**
     * 请求成功
     */
    OK(300, "请求成功"),

    /**
     * 请求超时
     */
    TIME_OUT(500, "请求超时"),

    /**
     * 网关超时
     */
    BAD_GATEWAY(502, "网关不可用"),

    /**
     * 网关超时
     */
    GATEWAY_TIMEOUT(504, "网关超时");

    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 描述
     */
    private String description;

    TraceStatus(int statusCode, String description){
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    public static TraceStatus convert(int status){
        if(status <= OK.statusCode){
            return OK;
        }

        TraceStatus traceStatus = TraceStatus.ERROR;
        if(TIME_OUT.statusCode == status){
            traceStatus = TraceStatus.TIME_OUT;
        }else if(BAD_GATEWAY.statusCode == status){
            traceStatus = BAD_GATEWAY;
        }else if(GATEWAY_TIMEOUT.statusCode == status){
            traceStatus = GATEWAY_TIMEOUT;
        }

        return traceStatus;
    }

}