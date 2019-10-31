package com.dust.copybean.strategy;

import java.lang.reflect.Field;

@HandlerName("base")
public class BaseHandler implements CopyFieldHandler {


    @Override
    public Object copyField(Field field, Object source) {
        Object object = null;
        try {
            object = field.get(source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
