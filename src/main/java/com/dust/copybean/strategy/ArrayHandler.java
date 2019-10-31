package com.dust.copybean.strategy;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 数组处理器
 */
@HandlerName("array")
public class ArrayHandler implements CopyFieldHandler {

    @Override
    public Object copyField(Field field, Object source) {
        Object array = null;
        try {
            array = field.get(source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?> clazz = field.getType();

        int len = Array.getLength(array);
        Class<?> componentType = clazz.componentType();
        Object newArray = Array.newInstance(componentType, len);
        System.arraycopy(array, 0, newArray, 0, len);
        return newArray;
    }
}
