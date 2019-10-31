package com.dust.copybean;

import com.dust.copybean.strategy.TypeContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 对类进行复制
 */
public class Copy implements Behavior {

    /**
     * 配置类
     */
    private Configuration configuration;

    /**
     * 类型转换上下文
     */
    private TypeContext context;

    public static Copy getInstance(Configuration configuration) {
        return new Copy(configuration);
    }

    private Copy(Configuration configuration) {
        this.configuration = configuration;
        this.context = TypeContext.getInstance();
    }

    @Override
    public void copy(Object source, Object target) {
        Field[] sourceFields = configuration.getSource().getDeclaredFields();
        Field[] targetFields = configuration.getTarget().getDeclaredFields();

        Map<String, Field> targetFieldMaps = new HashMap<>(targetFields.length);
        for (Field field : targetFields) {
            targetFieldMaps.put(field.getName(), field);
        }

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);

            Object targetValue = context.fieldCopy(sourceField, source);

            String targetFieldName = configuration.get(sourceField.getName());
            Field field = targetFieldMaps.get(targetFieldName);
            if (Objects.isNull(field)) {
                continue;
            }
            field.setAccessible(true);

            try {
                field.set(target, targetValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
