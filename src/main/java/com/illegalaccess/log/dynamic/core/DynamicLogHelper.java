package com.illegalaccess.log.dynamic.core;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class DynamicLogHelper {

    public static final String DEBUG_HEADER_NAME = "X-Debug";

    public static boolean parseDebugHeader(RequestAttributes requestAttributes) {
        // 先判断 RequestHeader，用于区分线程
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String debug = request.getHeader(DynamicLogHelper.DEBUG_HEADER_NAME);
        boolean enabledDebug = Boolean.parseBoolean(debug);

        return enabledDebug ? true : DynamicLogContext.debugEnabled();
    }

    public static boolean debugEnabledFromHeader() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return false;
        }

        return parseDebugHeader(requestAttributes);
    }

}
