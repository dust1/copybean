package com.dust.copy;

/**
 * 复制工厂
 *
 * 创建复制任务
 */
public interface CopyFactory {

    static CopyFactory getInstance() {
        CopyFactoryImpl impl = new CopyFactoryImpl();
        impl.init();
        return impl;
    }

    /**
     * 根据给定的两个实体对象创建复制任务
     */
    CopyTask getTask(Object source, Object target);
}
