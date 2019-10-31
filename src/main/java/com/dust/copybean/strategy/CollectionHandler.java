package com.dust.copybean.strategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 容器
 */
@HandlerName("collection")
public class CollectionHandler implements CopyFieldHandler {

    @Override
    public Object copyField(Field field, Object source) {
        Class<?> clazz = field.getType();
        Object value = null;
        try {
            value = field.get(source);
        } catch (IllegalAccessException e) {
            return null;
        }

        Object object = null;
        if (clazz.isAssignableFrom(List.class)) {
            object = new ArrayList<>((List) value);
        } else if (clazz.isAssignableFrom(Map.class)) {
            object = new HashMap<>((Map) value);
        } else {
            return null;
        }
        return object;
    }


}
