package com.dust.util;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 通用的类处理
 */
public class ClassUtil {

    /**
     * 根据类获取可以操作变量数组
     */
    public static Field[] getField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        return fields;
    }

    /**
     * 获取目标实体的目标字段的值
     *
     * 如果不存在或者出现异常则返回null
     */
    public static Object getValueByField(Field field, Object obj) {
        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            field.setAccessible(true);
            return getValueByFieldError(field, obj);
        }
        return result;
    }

    /**
     * 根据变量对目标对象进行赋值
     *
     * 如果成功则返回true，否则返回false
     */
    public static boolean setValueByField(Field field, Object object, Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            field.setAccessible(true);
            return setValueByFieldError(field, object, value);
        }
        return true;
    }

    private static boolean setValueByFieldError(Field field, Object object, Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static Object getValueByFieldError(Field field, Object object) {
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
