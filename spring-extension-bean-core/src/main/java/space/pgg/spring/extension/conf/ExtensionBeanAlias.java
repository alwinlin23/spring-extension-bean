package space.pgg.spring.extension.conf;

import lombok.NonNull;

/**
 * extension bean alias generator
 *
 * @author pgg
 * @since 2020-05-26 00:09:26
 */
public class ExtensionBeanAlias {

    public static final String PREFIX = "ext-bean";

    /**
     * generate extension bean alias (using as spring bean alias)
     *
     * @param extensionInterface extension interface
     * @param caseName           case name
     * @return bean alias
     */
    public static String of(@NonNull Class<?> extensionInterface, @NonNull String caseName) {
        return String.format("%s-%s-%s", PREFIX, extensionInterface.getCanonicalName(), caseName.trim());
    }

}
