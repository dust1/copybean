package com.dust.copybean;


/**
 * 变量哈希映射表
 *
 * 通过传入源对象实体的变量名称获取目标对象实体的变量名称
 */
public interface FieldHash {

    /**
     * 根据源数据对象的变量名获取目标对象的变量名
     * @param sourceFieldName 源数据对象的变量名
     */
    String get(String sourceFieldName);

}
