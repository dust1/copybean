package com.dust.copybean;

import java.util.Objects;
import java.util.function.Function;

/**
 * 配置
 */
public class Config implements Configuration {

    private Class<?> sourceClass;

    private Class<?> targetClass;

    private Function<String, String> fieldHash;

    Config(Class<?> source, Class<?> target, Function<String, String> fieldHash) {
        this.sourceClass = source;
        this.targetClass = target;
        this.fieldHash = fieldHash;
    }


    @Override
    public Class<?> getSource() {
        return Objects.requireNonNull(sourceClass);
    }

    @Override
    public Class<?> getTarget() {
        return Objects.requireNonNull(targetClass);
    }

    @Override
    public String get(String sourceFieldName) {
        return Objects.requireNonNull(fieldHash).apply(sourceFieldName);
    }
}
