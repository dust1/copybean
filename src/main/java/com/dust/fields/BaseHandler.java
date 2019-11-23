package com.dust.fields;

import com.dust.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * 基础数据类型处理
 */
public class BaseHandler implements FieldHandler {

    private static final Logger logger = Logger.getLogger(BaseHandler.class.getName());

    @Override
    public void handler(Field sourceField, Field targetField, Object source, Object target) {
        Object sourceFieldValue = ClassUtil.getValueByField(sourceField, source);
        boolean result = ClassUtil.setValueByField(targetField, target, sourceFieldValue);
        if (!result) {
            logger.warning("变量：" + sourceField.getName() + "赋值失败");
        }
    }
}
