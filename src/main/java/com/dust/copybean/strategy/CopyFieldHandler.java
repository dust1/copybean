package com.dust.copybean.strategy;

import java.lang.reflect.Field;

/**
 * 参数复制处理器
 *
 * 用于复制参数
 */
public interface CopyFieldHandler {

    /**
     * 从给定对象实例中获取该变量的一个复制
     * @param field 要获取复制的变量
     * @param source 目标对象
     */
    Object copyField(Field field, Object source);

}
