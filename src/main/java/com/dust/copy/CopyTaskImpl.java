package com.dust.copy;

import java.util.List;

public class CopyTaskImpl implements CopyTask {

    private List<CopyFactoryImpl.FieldValue> fieldValues;

    private Object source;

    private Object target;

    CopyTaskImpl(List<CopyFactoryImpl.FieldValue> fieldValues,
                 Object source, Object target) {
        this.fieldValues = fieldValues;
        this.source = source;
        this.target = target;
    }

    @Override
    public void start() {
        for (CopyFactoryImpl.FieldValue fieldValue : fieldValues) {
            fieldValue.start(source, target);
        }
    }

}
