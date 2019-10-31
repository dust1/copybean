package com.dust.copybean;

/**
 * 对象复制类
 */
public interface Behavior {

    /**
     * 开始复制
     * @param source 源实例
     * @param target 目标实例
     */
    void copy(Object source, Object target);

}
