package jdk8.demo02;

import java.lang.annotation.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-24 17:01
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Hint {
    String value();
}
