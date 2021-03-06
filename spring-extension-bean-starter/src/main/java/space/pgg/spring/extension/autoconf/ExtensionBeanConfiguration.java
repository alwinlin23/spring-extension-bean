package space.pgg.spring.extension.autoconf;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import space.pgg.spring.extension.annotation.EnableExtensionBean;
import space.pgg.spring.extension.conf.ExtensionBeanScanner;
import space.pgg.spring.extension.exception.ErrorEnums;

/**
 * ExtensionBean configuration
 *
 * @author pgg
 * @since 2020-05-25 23:43:56
 */
public class ExtensionBeanConfiguration implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    public static final String BOOTSTRAP_BASE_PACKAGE = "space.pgg.spring.extension";

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
        BeanDefinitionRegistry beanDefinitionRegistry) {
        // 0. get and check attributes from @EnableExtension
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
            annotationMetadata.getAnnotationAttributes(EnableExtensionBean.class.getName()));
        String[] extensionBeanBasePackages = attributes.getStringArray(EnableExtensionBean.BASE_PACKAGES);
        if (extensionBeanBasePackages == null || extensionBeanBasePackages.length == 0) {
            ErrorEnums.SCAN_BASE_PACKAGES_NOT_SET.throwsException(null, null, null, null);
        }
        // 1. scan @ExtensionBean
        ExtensionBeanScanner extensionBeanScanner = new ExtensionBeanScanner(beanDefinitionRegistry);
        if (this.resourceLoader != null) {
            extensionBeanScanner.setResourceLoader(resourceLoader);
        }
        extensionBeanScanner.scan(extensionBeanBasePackages);
        // 2. scan some other utils component
        ClassPathBeanDefinitionScanner utilityScanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        utilityScanner.scan(BOOTSTRAP_BASE_PACKAGE);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
