package com.dust.copybean;

import java.util.function.Function;

/**
 * 类型配置接口
 *
 * 这里配置复制类与目标类的类型
 */
public interface Configuration {

    Class<?> getSource();

    Class<?> getTarget();

    String get(String sourceFieldName);

    static Configuration of(Class<?> source, Class<?> target, Function<String, String> fieldHash) {
        return new Config(source, target, fieldHash);
    }

    static Configuration of(Class<?> source, Class<?> target) {
        return new Config(source, target, f -> f);
    }

}
