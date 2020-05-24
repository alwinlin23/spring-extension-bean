package space.pgg.spring.extension.error;

/**
 * Extension bean not found exception
 *
 * @author pgg
 * @since 2020-05-16 21:14:02
 */
public class ExtensionBeanNotFoundException extends AbstractExtensionBeanException {

    private static final long serialVersionUID = 6501376668704817962L;

    public ExtensionBeanNotFoundException(String msg) {
        super(msg);
    }

    public ExtensionBeanNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
