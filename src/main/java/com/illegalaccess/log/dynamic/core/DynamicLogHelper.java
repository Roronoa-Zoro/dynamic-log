package com.illegalaccess.log.dynamic.core;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class DynamicLogHelper {

    public static final String DEBUG_HEADER_NAME = "X-Debug";

    public static boolean parseDebugHeader(RequestAttributes requestAttributes) {
        // 先判断 RequestHeader，用于区分线程
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        // 按照请求参数判断，实际生产环境可以开发功能给管理人员功能，将用户唯一标示放入缓存或者session
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String debug = request.getHeader(DynamicLogHelper.DEBUG_HEADER_NAME);
        boolean enableDebug = Boolean.parseBoolean(debug);
        if (enableDebug) {
            return enableDebug;
        }

        return DynamicLogContext.debugEnabled();
    }

}
