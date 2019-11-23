package com.dust.copy;

import com.dust.annotation.CopyTag;
import com.dust.copybean.strategy.CopyFieldHandler;
import com.dust.fields.FieldHandler;
import com.dust.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * 复制工厂
 * <p>
 * 创建对象复制的任务
 */
public class CopyFactoryImpl implements CopyFactory {

    private Map<String, FieldHandler> handlerMap = new HashMap<>();

    /**
     * 基础数据类型集合
     */
    private static Set<Class<?>> baseSet = Set.of(Integer.class, int.class,
            Short.class, short.class, Byte.class, byte.class, Long.class, long.class,
            Double.class, double.class, Float.class, float.class, Boolean.class, boolean.class,
            Character.class, char.class);

    /**
     * 初始化复制相关的策略
     */
    void init() {
        //添加处理器
        handlerMap.put("array", FieldHandler.array());
        handlerMap.put("base", FieldHandler.base());
    }

    public CopyTask getTask(Object source, Object target) {
        Class sourceClass = source.getClass();
        Class targetClass = target.getClass();

        Field[] sourceFields = ClassUtil.getField(sourceClass);
        Field[] targetFields = ClassUtil.getField(targetClass);

        Map<String, Field> targetMaps = Arrays.stream(targetFields)
                .collect(toMap(Field::getName, f -> f));

        List<FieldValue> fieldValues = Arrays.stream(sourceFields)
                .map(FieldValue::new)
                .peek(f -> f.addTarget(targetMaps.get(f.getTargetName())))
                .filter(f -> f.mountHandler(handlerMap))
                .collect(toList());

        return new CopyTaskImpl(fieldValues, source, target);
    }

    /**
     * 字段与值的关联关系
     */
    static class FieldValue {

        private Field sourceField;

        private Field targetField;

        private FieldHandler fieldHandler;

        FieldValue(Field field) {
            this.sourceField = field;
        }

        void addTarget(Field target) {
            if (Objects.isNull(target)) {
                return;
            }
            this.targetField = target;

        }

        boolean mountHandler(Map<String, FieldHandler> handlerMap) {
            //比较两个类型
            Optional<String> type = typeComparison();
            if (type.isEmpty()) {
                return false;
            }

            this.fieldHandler = handlerMap.get(type.get());
            return true;
        }

        /**
         * 获取源字段要复制过去的目标字段
         */
        private String getTargetName() {
            CopyTag copyTag = sourceField.getAnnotation(CopyTag.class);
            if (Objects.isNull(copyTag)) {
                return sourceField.getName();
            } else {
                return copyTag.target();
            }
        }

        /**
         * 比较字段源类型与目标类型，只有两者类型相同并且属于基础数据类型以及基础数据类型组成的数组的时候才可以
         */
        private Optional<String> typeComparison() {
            Class sourceFieldClass = sourceField.getType();
            Class targetFieldClass = Objects.isNull(targetField) ? null : targetField.getType();

            if (!sourceFieldClass.equals(targetFieldClass)) {
                return Optional.empty();
            }

            if (sourceFieldClass.isArray() && isBase(sourceFieldClass.getComponentType())) {
                return Optional.of("array");
            } else if (isBase(sourceFieldClass)) {
                return Optional.of("base");
            } else {
                return Optional.empty();
            }
        }

        /**
         * 判断给定类型是否是基础数据类型以及String
         */
        private boolean isBase(Class clazz) {
            return baseSet.contains(clazz) || clazz.equals(String.class);
        }

        /**
         * 进行字段复制
         */
        void start(Object source, Object target) {
            fieldHandler.handler(sourceField, targetField, source, target);
        }

    }

}
