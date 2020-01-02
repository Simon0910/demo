package com.example.mvc.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 18:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SecretBody {
    // TODO: 2019/10/25
    // @AliasFor("jsonPathOut")
    String jsonPathIn() default "";

    // @AliasFor("jsonPathIn")
    String jsonPathOut() default "";
}
