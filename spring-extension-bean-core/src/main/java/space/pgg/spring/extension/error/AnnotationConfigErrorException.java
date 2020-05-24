package space.pgg.spring.extension.error;

/**
 * annotation config error
 *
 * @author pgg
 * @since 2020-05-16 21:11:07
 */
public class AnnotationConfigErrorException extends AbstractExtensionBeanException {

    private static final long serialVersionUID = -8437798228643833473L;

    public AnnotationConfigErrorException(String msg) {
        super(msg);
    }

    public AnnotationConfigErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
