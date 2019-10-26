package com.dust.copybean;


import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 对象转换工厂，一个工厂表示一种类型转化为另一种类型
 *
 * 如果要新建转化关系，则需要重新创建一个转换工厂
 */
public interface CopyFactory<U, T> {

    /**
     * 开始复制
     * @param source 源实例
     * @param target 目标实例
     */
    void copy(U source, T target);

    /**
     * 在执行完对象转换后调用HandlerTarget参数对象，并传入转换后的对象
     * @param source 源数据对象
     * @param target 目标对象
     * @param next 后续操作
     */
    void copy(U source, T target, Consumer<T> next);

    /**
     * 直接通过传入函数来创建对应的源对象、目标对象实例
     * @param sourceCreate 创建源对象的函数
     * @param targetCreate 创建目标对象的函数
     */
    void copy(Supplier<U> sourceCreate, Supplier<T> targetCreate);

    /**
     * 在执行完转换操作后调用参数为目标类型的后续函数
     * @param sourceCreate 创建源对象的函数
     * @param targetCreate 创建目标对象的函数
     * @param next 后续操作
     */
    void copy(Supplier<U> sourceCreate, Supplier<T> targetCreate, Consumer<T> next);

}
