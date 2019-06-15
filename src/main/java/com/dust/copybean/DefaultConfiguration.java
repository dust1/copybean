package com.dust.copybean;

import java.util.*;

/**
 * 默认配置实现
 */
public class DefaultConfiguration extends Configuration {

    private Set<String> skipPrefix;

    private Map<String, Set<String>> nameMaps;

    /**
     * 优先级，默认先进性前缀忽略，后进行映射
     */
    private int priority = 1;

    private DefaultConfiguration(Builder builder) {
        this.nameMaps = builder.nameMaps;
        this.skipPrefix = builder.skipPrefix;
        this.priority = builder.priority;
    }

    @Override
    String toTargetField(String sourceFieldName) {

        return null;
    }

    private String checkSkipPrefix(String fieldName) {
        Iterator<String> iterator = skipPrefix.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();

        }
        return null;
    }


    /**
     * Builder
     */
    public static class Builder {

        private Set<String> skipPrefix;

        private Map<String, Set<String>> nameMaps;

        private int priority;

        private Builder() {
            this.skipPrefix = new HashSet<>();
            this.nameMaps = new HashMap<>();
            this.priority = 1;
        }

        /**
         * 添加前缀
         * @param prefix 前缀
         */
        public Builder skipPrefix(String prefix) {
            skipPrefix.add(prefix);
            return this;
        }

        /**
         * 添加单纯的名称映射
         * @param sourceFieldName 源文件字段名称
         * @param targetFieldName 目标文件字段名称
         */
        public Builder addNameMap(String sourceFieldName,
                              String targetFieldName) {
            Set<String> targetFieldNames = nameMaps.getOrDefault(sourceFieldName, new HashSet<>());
            targetFieldNames.add(targetFieldName);
            nameMaps.put(sourceFieldName, targetFieldNames);
            return this;
        }

        public Builder prefixPriority() {
            this.priority = 1;
            return this;
        }

        private Builder mapPriority() {
            this.priority = -1;
            return this;
        }

        public DefaultConfiguration build() {
            return new DefaultConfiguration(this);
        }

    }

    /**
     * 构建单词树
     */
    static class Node {

    }

}
