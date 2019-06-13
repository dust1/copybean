package com.dust.copybean;


/**
 * 定义对配置的添加/删除/获取操作的接口
 */
public interface Configurable {

    /**
     * 设置配置
     */
    void setConfig(Configuration conf);

    /**
     * 获取配置
     */
    Configuration getConfig();

    /**
     * 删除配置
     */
    void removeConfig();

}
