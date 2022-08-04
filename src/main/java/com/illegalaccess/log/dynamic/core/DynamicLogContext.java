package com.illegalaccess.log.dynamic.core;

import com.illegalaccess.log.dynamic.core.base.DefaultLogLevelDecider;
import com.illegalaccess.log.dynamic.core.base.LogLevelDecider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class DynamicLogContext {

    private static ThreadLocal<Boolean> context = new ThreadLocal<>();

    public static boolean debugEnabled() {
        return Boolean.TRUE.equals(context.get());
    }

    public static void enableDebug() {
        context.set(true);
    }

    public static void clear() {
        context.remove();
    }

    public static List<LogLevelDecider> loadLogLevelDecider() {
        ServiceLoader<LogLevelDecider> serviceLoader =ServiceLoader.load(LogLevelDecider.class);
        Iterator<LogLevelDecider> iterator = serviceLoader.iterator();
        List<LogLevelDecider> list = new ArrayList<>();
        list.add(new DefaultLogLevelDecider());
        if (iterator == null) {
            return list;
        }

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }
}
