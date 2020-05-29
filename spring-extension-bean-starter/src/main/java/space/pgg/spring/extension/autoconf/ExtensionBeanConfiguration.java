package space.pgg.spring.extension.autoconf;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import space.pgg.spring.extension.annotation.EnableExtensionBean;
import space.pgg.spring.extension.error.ErrorEnums;

/**
 * ExtensionBean configuration
 *
 * @author pgg
 * @since 2020-05-25 23:43:56
 */
@ComponentScan(ExtensionBeanConfiguration.BOOTSTRAP_BASE_PACKAGE)
public class ExtensionBeanConfiguration implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    public static final String BOOTSTRAP_BASE_PACKAGE = "space.pgg.spring.extension";

    private ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
        BeanDefinitionRegistry beanDefinitionRegistry) {
        // 0. get attributes from @EnableExtension
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
            annotationMetadata.getAnnotationAttributes(EnableExtensionBean.class.getName()));
        // 1. check attributes
        String[] extensionBeanBasePackages = attributes.getStringArray(EnableExtensionBean.BASE_PACKAGES);
        if (extensionBeanBasePackages == null || extensionBeanBasePackages.length == 0) {
            ErrorEnums.BASE_PACKAGES_NOT_SET.throwingException(null, null, null, null);
        }
        // 2. scan @ExtensionBean
        // 3. scan some other utils component
    }
}
