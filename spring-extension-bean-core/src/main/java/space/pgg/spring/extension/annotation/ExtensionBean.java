package space.pgg.spring.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Extension Bean
 *
 * @author pgg
 * @since 2020-05-25 23:58:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(ExtensionBeans.class)
public @interface ExtensionBean {

    /**
     * extension implementation for the specified interface
     */
    Class<?> forInterface();

    /**
     * supported cases
     */
    String[] forCase() default {};

    /**
     * mark the annotated class as default implementation for the extension interface
     */
    boolean isDefault() default false;

    /**
     * wired default case name to avoid unnecessary case name conflictions
     */
    String DEFAULT_CASE_NAME = "#_DEFAULT_CASE_#";
}
