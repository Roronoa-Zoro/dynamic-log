package com.illegalaccess.log.dynamic.core.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.illegalaccess.log.dynamic.core.base.DynamicLogFilter;
import org.slf4j.Marker;

public class LogbackDynamicLogFilter extends TurboFilter implements DynamicLogFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        boolean skip = logger.getName().startsWith("com.illegalaccess.log.dynamic.core.logback");
        if (skip || level != Level.DEBUG) {
            return FilterReply.NEUTRAL;
        }

        return dynamicFilter() ? FilterReply.ACCEPT : FilterReply.NEUTRAL;
    }
}
