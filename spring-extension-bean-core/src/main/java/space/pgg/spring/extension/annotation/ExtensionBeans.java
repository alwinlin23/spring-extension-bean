package space.pgg.spring.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Extension bean annotation list
 *
 * @author pgg
 * @since 2020-05-30 01:18:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtensionBeans {

    /**
     * extension beans
     */
    ExtensionBean[] value() default {};

}
