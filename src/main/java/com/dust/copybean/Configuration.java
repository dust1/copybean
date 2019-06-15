package com.dust.copybean;


/**
 * 配置类。
 * 主要有两种配置：
 * <ul>
 *     <li>
 *         1:前缀忽略
 *     </li>
 *     <li>
 *         2:名称映射
 *     </li>
 * </ul>
 * 这两种可以混合使用，同时还能设定优先级。
 * 设定两者配置依据那个配置优先。
 *
 * 配置类完成的功能：
 * source类传入一个字段名称，Config根据它自身的配置，返回一个target的字段。
 * 然后根据返回的目标字段从target中查找。如果存在则进行赋值。
 */
public abstract class Configuration {

    /**
     * 默认的配置
     */
    public static final Configuration DEFAULT_CONFIG = new Configuration() {
        @Override
        String toTargetField(String sourceFieldName) {
            return sourceFieldName;
        }
    };

    /**
     * 根据source的字段名称返回配置文件修改后的target字段名称
     *
     * 用户也可以自己继承该类来实现这个转换方法
     * @param sourceFieldName 要转换的source字段名称
     */
    abstract String toTargetField(String sourceFieldName);

}
