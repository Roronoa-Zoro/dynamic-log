package com.illegalaccess.log.dynamic.core.log4j2;

import com.illegalaccess.log.dynamic.core.DynamicLogHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Plugin(name = "DynamicLogFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
public class Log4j2DynamicLogFilter extends AbstractFilter {

    public Log4j2DynamicLogFilter(Result onMatch, Result onMismatch) {
        super(onMatch, onMismatch);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        return debugFilter(logger);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        return debugFilter(logger);
    }

    private Result debugFilter(Logger logger) {
        boolean skip = logger.getName().startsWith("com.illegalaccess.log.dynamic.core.log4j2");
        if (skip) {
            return Result.NEUTRAL;
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return Result.NEUTRAL;
        }

        boolean debugEnabled = DynamicLogHelper.parseDebugHeader(requestAttributes);
        if (debugEnabled) {
            return Result.ACCEPT;
        }

        return Result.NEUTRAL;
    }

    @Override
    public String toString() {
        return "Log4j2DynamicLogFilter";
    }

    @PluginBuilderFactory
    public static Log4j2DynamicLogFilter.Builder newBuilder() {
        return new Builder();
    }

    public static class Builder extends AbstractFilterBuilder<Builder>
            implements org.apache.logging.log4j.core.util.Builder<Log4j2DynamicLogFilter> {

        public Builder() {
        }

        public Log4j2DynamicLogFilter build() {
            return new Log4j2DynamicLogFilter(this.getOnMatch(), this.getOnMismatch());
        }
    }
}
