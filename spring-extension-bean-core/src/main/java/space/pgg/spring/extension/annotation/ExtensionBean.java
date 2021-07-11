package space.pgg.spring.extension.annotation;

import java.lang.annotation.*;

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
     * default case name (weired enough to avoid case name conflictions)
     */
    String DEFAULT_CASE = "#_DEFAULT_CASE_#";
}
