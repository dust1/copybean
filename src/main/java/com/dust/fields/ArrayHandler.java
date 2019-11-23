package com.dust.fields;

import com.dust.util.ClassUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 数据处理
 */
public class ArrayHandler implements FieldHandler {

    private static final Logger logger = Logger.getLogger(ArrayHandler.class.getName());

    @Override
    public void handler(Field sourceField, Field targetField, Object source, Object target) {
        Object sourceValue = ClassUtil.getValueByField(sourceField, source);

        boolean result = false;
        if (Objects.isNull(sourceValue)) {
            result = ClassUtil.setValueByField(targetField, target, null);
        } else {
            int len = Array.getLength(sourceValue);
            Object newArray = Array.newInstance(sourceField.getType().componentType(), len);
            System.arraycopy(sourceValue, 0, newArray, 0, len);
            result = ClassUtil.setValueByField(targetField, target, newArray);
        }

        if (!result) {
            logger.warning("数组变量：" + sourceField.getName() + "赋值失败");
        }
    }
}
