package com.dust.copy;

/**
 * 复制类接收两个实体并将原实体的值复制到另一个实体
 */
public interface Copy {

    static Copy create() {
        return new CopyImpl();
    }

    /**
     * 将source实体的变量复制到targte实体
     */
    void copy(Object source, Object target);

}
