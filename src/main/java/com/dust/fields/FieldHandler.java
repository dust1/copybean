package com.dust.fields;

import java.lang.reflect.Field;

/**
 * 字段处理
 * 当前只支持基本数据类型和基本数据类型组成的数组
 */
public interface FieldHandler {

    /**
     * 获取基础数据类型的字段处理器
     */
    static FieldHandler base() {
        return new BaseHandler();
    }

    /**
     * 获取基础数据类型的数组的字段处理器
     */
    static FieldHandler array() {
        return new ArrayHandler();
    }

    /**
     * 两个实体对象的两个目标变量进行值的复制
     */
    void handler(Field targetField, Field sourceField, Object source, Object target);

}
