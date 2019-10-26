package com.dust.copybean.utils;

import com.dust.copybean.FieldHash;

/**
 * 类的通用处理
 */
public class ClassUtils {

    public static void copyBean(Object source, Object target,
                                Class sourceClass, Class targetClass,
                                FieldHash fieldHash) {
        sourceClass.getDeclaredFields();
    }

}
