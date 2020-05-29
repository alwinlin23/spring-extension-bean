package space.pgg.spring.extension.error;

/**
 * error enums
 *
 * @author pgg
 * @since 2020-05-16 21:16:19
 */
public enum ErrorEnums {

    /**
     * extension bean case name not configured
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
            return new ExtensionBeanNotFoundException(errMsg, cause);
        }
    }),

    /**
     * extension bean duplicate case name
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
            return null;
        }
    }),

    /**
     * property scanBasePackages in @EnableExtension is not set
     */
    BASE_PACKAGES_NOT_SET(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return BASE_PACKAGES_NOT_SET.name();
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
     * extension bean not found at runtime
     */
    EXTENSION_BEAN_NOT_FOUND_AT_RUNTIME(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return EXTENSION_BEAN_NOT_FOUND_AT_RUNTIME.name();
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
     * the extension bean has not implemented the extension interface mentioned in the @ExtensionBean
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

    UNKNOWN_ERROR(new AbstractErrorDescription() {
        @Override
        protected String errorCode() {
            return UNKNOWN_ERROR.name();
        }

        @Override
        protected String errorMessage(Class<?> extensionInterface, Class<?> extensionClass, String caseName,
            Throwable cause) {
            return String.format("Unknown error : interface[%s]-case[%s]");
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
     * throws specific exception
     *
     * @param extensionInterface extension interface
     * @param extensionClass     extension class
     * @param caseName           case name
     * @param cause              error cause
     */
    public void throwingException(Class<?> extensionInterface, Class<?> extensionClass,
        String caseName, Throwable cause) {
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
