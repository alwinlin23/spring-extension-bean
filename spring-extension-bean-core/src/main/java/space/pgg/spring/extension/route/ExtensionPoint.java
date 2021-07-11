package space.pgg.spring.extension.route;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.conf.ExtensionBeanAlias;
import space.pgg.spring.extension.exception.ErrorEnums;

/**
 * Extension point
 *
 * @author pgg
 * @since 2020-05-30 01:50:44
 */
@Component
public class ExtensionPoint implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //TODO 2020/5/30 2:38 上午 pgg application.properties
    /**
     * 缓存一波
     * cache extension bean up
     */
    @SuppressWarnings("rawtypes")
    private static final Cache<String, ExtensionBeanBroker> BROKER_CACHE =
            Caffeine.newBuilder().maximumSize(1024).build();

    /**
     * 找个代理包一下
     * 1. 走缓存提高性能
     * 2. 吃颗语法糖, 写起来更流程
     * <p>
     * wrap with a broker
     * 1. take the advantage of cache
     * 2. syntactic sugar with fluent api
     *
     * @param extensionInterface 扩展点接口
     *                           extension interface
     * @param <T>                扩展点接口对应的类型
     *                           extension bean type
     * @return extension bean broker
     */
    @SuppressWarnings("unchecked")
    public static <T> ExtensionBeanBroker<T> on(@NonNull Class<T> extensionInterface) {
        ExtensionBeanBroker<T> broker = BROKER_CACHE.getIfPresent(extensionInterface.getCanonicalName());
        if (broker == null) {
            broker = new ExtensionBeanBroker<>(extensionInterface);
            BROKER_CACHE.put(extensionInterface.getCanonicalName(), broker);
        }
        return broker;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ExtensionPoint.applicationContext = applicationContext;
    }

    @Data
    public static class ExtensionBeanBroker<T> {

        @NonNull
        private Class<T> extensionInterface;

        @SuppressWarnings("unchecked")
        public T of(String caseName) {
            T bean = null;
            try {
                String alias = ExtensionBeanAlias.of(extensionInterface, caseName);
                if (!applicationContext.containsBean(alias)) {
                    alias = ExtensionBeanAlias.of(extensionInterface, ExtensionBean.DEFAULT_CASE);
                }
                bean = (T) applicationContext.getBean(alias);
            } catch (NoSuchBeanDefinitionException e) {
                ErrorEnums.EXTENSION_BEAN_NOT_FOUND.throwsException(extensionInterface, null, caseName, e);
            } catch (Throwable e) {
                ErrorEnums.UNKNOWN_ERROR.throwsException(extensionInterface, null, caseName, e);
            }
            return bean;
        }

    }

}
