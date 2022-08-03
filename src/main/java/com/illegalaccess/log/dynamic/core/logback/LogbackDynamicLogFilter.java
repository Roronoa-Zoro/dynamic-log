package com.illegalaccess.log.dynamic.core.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.illegalaccess.log.dynamic.core.DynamicLogHelper;
import org.slf4j.Marker;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class LogbackDynamicLogFilter extends TurboFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        boolean isMyLogger = logger.getName().startsWith("com.illegalaccess.log.dynamic.core.logback");
        if (!isMyLogger) {
            return FilterReply.NEUTRAL;
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return FilterReply.NEUTRAL;
        }

        boolean debugEnabled = DynamicLogHelper.parseDebugHeader(requestAttributes);
        if (debugEnabled) {
            return FilterReply.ACCEPT;
        }

        return FilterReply.NEUTRAL;
    }
}
