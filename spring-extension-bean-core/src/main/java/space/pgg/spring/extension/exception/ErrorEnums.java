package space.pgg.spring.extension.exception;

import space.pgg.spring.extension.annotation.ExtensionBean;

/**
 * Error definition enums
 *
 * @author pgg
 * @since 2020-05-16 21:16:19
 */
public enum ErrorEnums {

    /**
     * 扩展点注解 {@link ExtensionBean} 未配置 extension bean case name not configured
     */
    CASE_NAME_NOT_CONFIGURED(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return CASE_NAME_NOT_CONFIGURED.name();
        }

        @Override
        protected String errorMessage(
            Class<?> extensionInterface, Class<?> extensionClass, String caseName, Throwable cause) {
            return String.format("Extension bean configuration error : "
                + "case name not configured in annotation @ExtensionBean on extension class [%s]",
                extensionClass.getCanonicalName());
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/22 2:19 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new AnnotationConfigErrorException(errMsg, cause);
        }
    }),

    /**
     * 扩展点场景名称重复 extension bean duplicate case name
     */
    DUPLICATE_CASE_NAME(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return DUPLICATE_CASE_NAME.name();
        }

        @Override
        protected String errorMessage(
            Class<?> extensionInterface, Class<?> extensionClass, String caseName, Throwable cause) {
            return String.format("Extension bean configuration error : interface[%s]-case[%s]-class[%s]",
                extensionInterface.getCanonicalName(), caseName, extensionClass.getCanonicalName());
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/22 2:19 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new AnnotationConfigErrorException(errMsg, cause);
        }
    }),

    /**
     * 通过 @EnableExtension 启用扩展点时, 扫描路径未配置 scanBasePackages in @EnableExtension is not set
     */
    SCAN_BASE_PACKAGES_NOT_SET(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return SCAN_BASE_PACKAGES_NOT_SET.name();
        }

        @Override
        protected String errorMessage(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
            Throwable cause) {
            return "Extension bean configuration error : property[scanBasePackages] in @EnableExtension is not set";
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/22 2:19 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new AnnotationConfigErrorException(errMsg, cause);
        }
    }),

    /**
     * 运行时根据场景名称未找到对应的扩展点 extension bean not found at runtime
     */
    EXTENSION_BEAN_NOT_FOUND(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return EXTENSION_BEAN_NOT_FOUND.name();
        }

        @Override
        protected String errorMessage(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
            Throwable cause) {
            return String.format(
                "Extension bean runtime error : the implementation class for interface[%s]-case[%s] is not found. "
                    + "Set a default implementation extension bean might help.",
                extensionInterface.getCanonicalName(), caseName);
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/22 2:19 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new ExtensionBeanNotFoundException(errMsg, cause);
        }
    }),

    /**
     * 扩展点一致性错误, 扩展点实现类未实现注解中标称的扩展点接口 the extension bean has not implemented the extension interface mentioned in
     * the @ExtensionBean
     */
    INTERFACE_CONSISTENCY_ERROR(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return INTERFACE_CONSISTENCY_ERROR.name();
        }

        @Override
        protected String errorMessage(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
            Throwable cause) {
            return String.format(
                "Extension bean configuration error : all the interfaces that [%s] has implemented "
                    + "does not consist with the configured extension interface[%s] in the @ExtensionBean",
                extensionClass.getCanonicalName(), extensionInterface.getCanonicalName());
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/22 2:19 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new AnnotationConfigErrorException(errMsg, cause);
        }
    }),

    /**
     * 未知错误 unknown error
     */
    UNKNOWN_ERROR(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return UNKNOWN_ERROR.name();
        }

        @Override
        protected String errorMessage(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
            Throwable cause) {
            return String.format("Unknown error : interface[%s]-case[%s]", extensionInterface.getCanonicalName(),
                caseName);
        }

        @Override
        protected String docURL() {
            //TODO 2020/5/30 2:23 上午 pgg
            return null;
        }

        @Override
        protected AbstractExtensionBeanException withException(String errMsg, Throwable cause) {
            return new UnknownException(errMsg, cause);
        }
    });

    /**
     * 抛一个指定的异常 throws a specific exception
     *
     * @param extensionInterface
     *            扩展点接口 extension interface
     * @param extensionClass
     *            扩展接口的实现类 the implementation class of the extension interface
     * @param caseName
     *            场景名称 case name
     * @param cause
     *            错误原因 error cause
     */
    public void throwsException(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
        Throwable cause) {
        throw this.errorDescription.exception(extensionInterface, extensionClass, caseName, cause);
    }

    /**
     * error description
     */
    private final AbstractErrorDescription errorDescription;

    ErrorEnums(AbstractErrorDescription errorDescription) {
        this.errorDescription = errorDescription;
    }

}
