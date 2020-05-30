package space.pgg.spring.extension.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.annotation.ExtensionBeans;
import space.pgg.spring.extension.error.ErrorEnums;

/**
 * annotation scanner for {@link ExtensionBean}
 *
 * @author pgg
 * @since 2020-05-26 00:47:52
 */
@Slf4j
public class ExtensionBeanScanner extends ClassPathBeanDefinitionScanner {

    public ExtensionBeanScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(ExtensionBean.class));
        this.addIncludeFilter(new AnnotationTypeFilter(ExtensionBeans.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) &&
            (beanDefinition.getMetadata().hasAnnotation(ExtensionBean.class.getName())
                || beanDefinition.getMetadata().hasAnnotation(ExtensionBeans.class.getName()));
    }

    @Override
    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        if (!this.getRegistry().containsBeanDefinition(definitionHolder.getBeanName())) {
            super.registerBeanDefinition(definitionHolder, registry);
        }
        registerExtensionBeanAlias(definitionHolder);
    }

    @SneakyThrows
    private void registerExtensionBeanAlias(BeanDefinitionHolder definitionHolder) {
        BeanDefinition beanDefinition = definitionHolder.getBeanDefinition();
        // 0. in case of Class not loaded
        Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
        // 1. register extension bean
        for (ExtensionBean extensionBean : beanClass.getDeclaredAnnotationsByType(ExtensionBean.class)) {
            register(definitionHolder, beanClass, extensionBean);
        }
    }

    /**
     * register extension bean as alias
     *
     * @param holder                  bean definition holder
     * @param beanClass               extension bean implementation class
     * @param extensionBeanAnnotation extension bean annotation
     */
    private void register(BeanDefinitionHolder holder, Class<?> beanClass, ExtensionBean extensionBeanAnnotation) {
        Class<?> extensionInterface = extensionBeanAnnotation.forInterface();
        // 0. check extension implementation interface consistency
        validateInterfaceConsistency(beanClass, extensionInterface);
        // 1. prepare case name list
        List<String> caseNameList = prepareCaseNameList(beanClass, extensionBeanAnnotation, extensionInterface);
        // 2. register extension bean in the form of spring bean alias
        for (String caseName : caseNameList) {
            String alias = ExtensionBeanAlias.of(extensionInterface, caseName);
            if (this.getRegistry().isBeanNameInUse(alias)) {
                ErrorEnums.DUPLICATE_CASE_NAME.throwingException(extensionInterface, beanClass, caseName, null);
            }
            this.getRegistry().registerAlias(holder.getBeanName(), alias);
            log.info("ExtensionBeanScanner.register extension bean[{}] for interface[{}] and case[{}].",
                alias, extensionInterface.getCanonicalName(), caseName);
        }
    }

    /**
     * prepare case name list
     *
     * @param beanClass               the implementation class of extension bean
     * @param extensionBeanAnnotation the ExtensionBean annotation
     * @param extensionInterface      extension interface
     * @return all the case names listed in the ExtensionBean annotation
     */
    private List<String> prepareCaseNameList(Class<?> beanClass, ExtensionBean extensionBeanAnnotation,
        Class<?> extensionInterface) {
        // 0. extract case name list from ExtensionBean annotation
        List<String> caseNameList = new ArrayList<>();
        Collections.addAll(caseNameList, extensionBeanAnnotation.forCase());
        if (extensionBeanAnnotation.isDefault()) {
            caseNameList.add(ExtensionBean.DEFAULT_CASE);
        }
        // 1. process default case
        if (caseNameList.isEmpty()) {
            ErrorEnums.CASE_NAME_NOT_CONFIGURED.throwingException(extensionInterface, beanClass, null, null);
        }
        return caseNameList;
    }

    /**
     * check extension implementation interface consistency
     *
     * @param beanClass          extension bean implementation
     * @param extensionInterface extension interface
     */
    private void validateInterfaceConsistency(Class<?> beanClass, final Class<?> extensionInterface) {
        boolean isConsistent = ClassUtils.getAllInterfaces(beanClass).stream().map(Class::getCanonicalName)
            .anyMatch(interfaceName -> interfaceName.equals(extensionInterface.getCanonicalName()));
        if (!isConsistent) {
            ErrorEnums.INTERFACE_CONSISTENCY_ERROR.throwingException(extensionInterface, beanClass, null, null);
        }
    }

}
