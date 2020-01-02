package com.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要swagger展示的枚举
 * 默认 支持 枚举中属性名为 value和desc
 * 如果不是，请设置valueName和descName
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerDisplayEnum {
    String valueName() default "value";

    String descName() default "desc";
}