package com.dust.copybean;

/**
 * 类复制接口
 */
public interface Eye {

    /**
     * 开始复制
     * @param source 源实例
     * @param target 目标实例
     * @param sourceClass 源类
     * @param targetClass 目标类
     */
    void copy(Object source, Object target,
              Class sourceClass, Class targetClass);

}
