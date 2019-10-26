package com.dust.copybean.example;

import com.dust.copybean.CopyFactory;
import com.dust.copybean.utils.ClassUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 默认的对象复制工厂
 */
public class DefaultCopyBeanFactory<U, T> implements CopyFactory<U, T> {


    @Override
    public void copy(U source, T target) {
        ClassUtils.copyBean(source, target, source.getClass(), target.getClass(), t -> t);
    }

    @Override
    public void copy(U source, T target, Consumer<T> next) {
        copy(source, target);
        next.accept(target);
    }

    @Override
    public void copy(Supplier<U> sourceCreate, Supplier<T> targetCreate) {
        copy(sourceCreate.get(), targetCreate.get());
    }

    @Override
    public void copy(Supplier<U> sourceCreate, Supplier<T> targetCreate, Consumer<T> next) {
        U source = sourceCreate.get();
        T target = targetCreate.get();
        copy(source, target);

        next.accept(target);
    }
}
