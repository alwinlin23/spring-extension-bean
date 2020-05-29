package space.pgg.spring.extension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import space.pgg.spring.extension.autoconf.ExtensionBeanConfiguration;

/**
 * Enable extension bean
 *
 * @author pgg
 * @since 2020-05-25 23:40:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ExtensionBeanConfiguration.class)
public @interface EnableExtensionBean {

    /**
     * base packages
     */
    String BASE_PACKAGES = "basePackages";

    /**
     * base packages that use extension bean    <br/>
     * something like spring's {@link ComponentScan#basePackages()}
     */
    String basePackages();

}
