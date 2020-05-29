package space.pgg.spring.extension.error;

/**
 * Error definition
 *
 * @author pgg
 * @since 2020-05-16 20:35:12
 */
public abstract class AbstractErrorDescription {

    public static final String ISSUE_URL = "https://github.com/alwinlin23/spring-extension-bean/issues";

    /**
     * produce a specific extension bean exception
     *
     * @param extensionInterface the extension interface
     * @param extensionClass     an implementation class of the extension interface
     * @param caseName           extension case name
     * @param cause              extension error cause (optional)
     * @return specific extension bean exception
     */
    public AbstractExtensionBeanException exception(Class<?> extensionInterface, Class<?> extensionClass,
        String caseName, Throwable cause) {
        String errMsg = String.format("[%s]-%s\nDocuments:%s\nSubmit issues:%s\n",
            errorCode(), errorMessage(extensionInterface, extensionClass, caseName, cause),
            docURL(), ISSUE_URL);
        return withException(errMsg, cause);
    }

    /**
     * error code
     *
     * @return error code
     */
    protected abstract String errorCode();

    /**
     * error message
     *
     * @param extensionInterface extension interface
     * @param extensionClass     implementation class of the extension interface
     * @param caseName           extension case name
     * @param cause              error cause
     * @return error message
     */
    protected abstract String errorMessage(Class<?> extensionInterface, Class<?> extensionClass,
        String caseName, Throwable cause);

    /**
     * document URL
     *
     * @return document URL related to the given error
     */
    protected abstract String docURL();

    /**
     * with specific exception
     *
     * @param errMsg error message
     * @param cause  error cause
     * @return specific extension bean exception
     */
    protected abstract AbstractExtensionBeanException withException(String errMsg, Throwable cause);

}
