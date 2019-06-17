package com.trace.common.constant.protocol;

/**
 * 传输协议
 * @author 364623395@qq.com
 */
public enum TransportProtocol {
    /**
     * http协议
     */
    HTTP("http协议"),

    /**
     * dubbo协议
     */
    DUBBO("dubbo协议");

    /**
     * 协议描述
     */
    private String description;

    TransportProtocol(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}