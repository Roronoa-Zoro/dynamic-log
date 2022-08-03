package com.illegalaccess.log.dynamic.core;

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
}
