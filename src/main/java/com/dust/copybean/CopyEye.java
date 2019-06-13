package com.dust.copybean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用于复制JavaBean当中的各项数据。
 * 同时还要提供状状态的转移。即，当source中参数的类型和target参数的类型不一致时，
 * 需要尝试将source的类型转化为target中的类型。
 * 由于会有组合，因此采用Builder来进行具体的配置
 * <p>
 *     复制方式：
 *     <ul>
 *         <li>
 *             1:直接复制
 *         </li>
 *         <li>
 *             2:忽略前缀
 *         </li>
 *         <li>
 *             3:自定义映射 - 专门针对source和target参数名称不一致但是在逻辑上表现为一致的情况
 *         </li>
 *     </ul>
 * </p>
 */
public class CopyEye extends Configured implements Eye {

    /**
     * 最基本的根据变量名称的复制
     * @param source 源实例
     * @param target 目标实例
     * @param sourceClass 源类
     * @param targetClass 目标类
     */
    @Override
    public void copy(Object source, Object target,
                            Class sourceClass, Class targetClass) {
        if (!checkConfig()) {
            setConfig(Configuration.DEFAUL_CONFIG);
        }

        Field[] sourceFields = sourceClass.getDeclaredFields();
        String[] targetFieldNames = new String[sourceFields.length];

        for (int i = 0; i < sourceFields.length; i++) {
            Field field = sourceFields[i];
            String targetFieldName = getConfig().toTargetField(field.getName());
            targetFieldNames[i] = targetFieldName;
        }

        for (int i = 0; i < targetFieldNames.length; i++) {
            String targetFieldName = targetFieldNames[i];
            Field targetField = null;
            try {
                targetField = targetClass.getDeclaredField(targetFieldName);
            } catch (NoSuchFieldException e) {
                System.out.println("target field can not found, skip it! >>>>>>>> " + targetFieldName);
                continue;
            }
            targetField.setAccessible(true);

            Field sourceField = sourceFields[i];
            sourceField.setAccessible(true);
            Object sourceValue = null;
            try {
                sourceValue = sourceField.get(source);
            } catch (IllegalAccessException e) {
                System.out.println("source field can not have access, skip it! >>>>>>>>> " + sourceField.getName());
                continue;
            }

            try {
                targetField.set(target, sourceValue);
            } catch (IllegalAccessException e) {
                System.out.println("target field can not have access, skip it! >>>>>>>>> " + sourceField.getName());
            }
        }
    }

}
