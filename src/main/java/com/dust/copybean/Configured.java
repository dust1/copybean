package com.dust.copybean;

/**
 * 配置抽象类，用于对配置实例的维护
 */
public abstract class Configured implements Configurable {

    private Configuration configuration;

    @Override
    public void setConfig(Configuration conf) {
        this.configuration = conf;
    }

    @Override
    public Configuration getConfig() {
        return this.configuration;
    }

    @Override
    public void removeConfig() {
        this.configuration = null;
    }

    /**
     * 是否已有配置
     */
    boolean checkConfig() {
        return this.configuration != null;
    }

}
