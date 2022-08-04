package com.illegalaccess.log.dynamic.core.base;

import com.illegalaccess.log.dynamic.core.DynamicLogHelper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class DefaultLogLevelDecider implements LogLevelDecider  {

    @Override
    public boolean filtered() {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return false;
        }

        boolean debugEnabled = DynamicLogHelper.parseDebugHeader(requestAttributes);
        if (debugEnabled) {
            return true;
        }

        return false;
    }
}
