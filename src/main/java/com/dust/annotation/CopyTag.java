package com.dust.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记要复制的实体的变量的变量名，copybean在复制的时候会根据改注解的变量名进行查找复制。
 *
 * 同时也只会对添加了该注解的变量进行复制
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyTag {

    String target();

}
