package com.trace.model;


import com.trace.common.constant.protocol.TransportProtocol;
import com.trace.common.constant.state.TraceStatus;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Trace
 * @author 364623395@qq.com
 */
@Data
@ToString
public class Trace implements Serializable {
    /**
     * traceId
     */
    private String traceId;

    /**
     * spanId
     */
    private String spanId;

    /**
     * 应用名
     */
    private String application;

    /**
     * 进程id
     */
    private long pid;

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 请求开始时间
     */
    private long startTime;

    /**
     * 请求结束时间
     */
    private long endTime;

    /**
     * 状态
     * @see TraceStatus
     */
    private String status;

    /**
     * 传输协议
     * @see TransportProtocol
     */
    private String protocol;

    /**
     * URL
     */
    private String uri;

    /**
     * Ext Fields
     */
    private Map<String, String> extFields;

    /**
     * Result
     */
    private String result;

    public static class Builder{
        private String traceId;
        private String spanId;
        private String application;
        private long pid;
        private String ip;
        private int port;
        private long startTime;
        private long endTime;
        private String status;
        private String protocol;
        private String uri;
        private Map<String, String> extFields;
        private String result;

        public Builder traceId(String traceId){
            this.traceId = traceId;
            return this;
        }

        public Builder spanId(String spanId){
            this.spanId = spanId;
            return this;
        }

        public Builder application(String application){
            this.application = application;
            return this;
        }

        public Builder pid(long processId){
            this.pid = processId;
            return this;
        }

        public Builder ip(String ip){
            this.ip = ip;
            return this;
        }

        public Builder port(int port){
            this.port = port;
            return this;
        }

        public Builder startTime(long startTime){
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(long endTime){
            this.endTime = endTime;
            return this;
        }

        public Builder status(String status){
            this.status = status;
            return this;
        }

        public Builder protocol(String protocol){
            this.protocol = protocol;
            return this;
        }

        public Builder uri(String uri){
            this.uri = uri;
            return this;
        }

        public Builder extFields(Map<String, String> extFields){
            this.extFields = extFields;
            return this;
        }

        public Builder result(String result){
            this.result = result;
            return this;
        }

        public Trace build(){
            Trace trace = new Trace();
            trace.traceId = this.traceId;
            trace.spanId = this.spanId;
            trace.application = this.application;
            trace.ip = this.ip;
            trace.port = this.port;
            trace.startTime = this.startTime;
            trace.endTime = this.endTime;
            trace.status = this.status;
            trace.protocol = this.protocol;
            trace.extFields = this.extFields;
            trace.result = this.result;
            trace.pid = this.pid;
            trace.uri = this.uri;
            return trace;
        }
    }

}