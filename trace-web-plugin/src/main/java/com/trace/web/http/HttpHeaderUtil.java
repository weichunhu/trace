package com.trace.web.http;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP HEADER
 * @author chunhua.wei
 */
public final class HttpHeaderUtil {

    public static String fromHeader(String header, HttpServletRequest request){
        return request.getHeader(header);
    }

}