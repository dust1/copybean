package com.dust.copybean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果两个类的所有参数名称以及类型都相同，则使用该注解标注类
 *
 * 这样就会直接复制整个类的所有同名变量
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyBean {
}
