package com.illegalaccess.log.dynamic.core.base;

public interface LogLevelDecider {

    /**
     * 如果可以开启debug，返回true，反之返回false
     * @return
     */
    boolean filtered();
}
