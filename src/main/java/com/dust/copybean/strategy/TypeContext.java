package com.dust.copybean.strategy;

import com.dust.copybean.utils.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 针对不同的变量类型执行不同的逻辑
 *
 * 当前有三种区分：
 * 1：数组，调用{@code clone()}方法进行复制
 * 2：基础数据类型以及@{@link String}类型,直接进行复制
 * 3：引用对象，这里只适用{@link java.util.Collection}以及{@link java.util.Map}两种类型，因为两种类型一个可以调用
 * {@link java.util.Collection#addAll(Collection)} 另一个可以调用{@link java.util.Map#putAll(Map)}来进行初始化
 */
public class TypeContext {

    /**
     * 基础数据类型集合
     */
    private static Set<Class<?>> baseSet = Set.of(Integer.class, int.class,
            Short.class, short.class, Byte.class, byte.class, Long.class, long.class,
            Double.class, double.class, Float.class, float.class, Boolean.class, boolean.class,
            Character.class, char.class);

    /**
     * 单例对象
     *
     * 添加volatile是为了避免初始化对象的时候进行指令重排序，
     * 如果对context的赋值发生在调用构造器方法之前，则会产生奇怪的bug
     */
    private volatile static TypeContext context;

    public static TypeContext getInstance() {
        if (Objects.isNull(context)) {
            synchronized (TypeContext.class) {
                if (Objects.isNull(context)) {
                    context = new TypeContext();
                }
            }
        }
        return context;
    }

    private Map<String, CopyFieldHandler> handlerMap;

    private TypeContext() {
        this.handlerMap = new HashMap<>();

        this.init();
    }

    private void init() {
        List<Class<CopyFieldHandler>> classes;
        try {
            classes = ClassUtils.getAllAssignedClass(CopyFieldHandler.class);
        } catch (ClassNotFoundException e) {
            return;
        }

        for (Class<CopyFieldHandler> clazz : classes) {
            if (clazz.isAnnotationPresent(HandlerName.class)) {
                HandlerName handlerName = clazz.getAnnotation(HandlerName.class);
                CopyFieldHandler handler = null;
                try {
                    handler = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                handlerMap.put(handlerName.value(), handler);
            }
        }
    }

    /**
     * 从给定对象实例中获取该变量的一个复制
     */
    public Object fieldCopy(Field field, Object source) {
        Class<?> fieldType = field.getType();

        if (fieldType.isArray()) {
            CopyFieldHandler handler = handlerMap.get("array");
            return handler.copyField(field, source);
        }

        if (baseSet.contains(field.getType()) || fieldType.equals(String.class)) {
            CopyFieldHandler handler = handlerMap.get("base");
            return handler.copyField(field, source);
        }

        CopyFieldHandler handler = handlerMap.get("collection");
        return handler.copyField(field, source);
    }

}
