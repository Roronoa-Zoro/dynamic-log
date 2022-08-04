package com.illegalaccess.log.dynamic.core.base;

import com.illegalaccess.log.dynamic.core.DynamicLogContext;

import java.util.List;

public interface DynamicLogFilter {

    default boolean dynamicFilter() {
        List<LogLevelDecider> list = DynamicLogContext.loadLogLevelDecider();
        if (list == null || list.isEmpty()) {
            return false;
        }

        for (LogLevelDecider decider : list) {
            if (decider.filtered()) {
                return true;
            }
        }

        return false;
    }
}
