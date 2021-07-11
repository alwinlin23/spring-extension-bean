package space.pgg.spring.extension.exception;

/**
 * Unknown exception
 *
 * @author pgg
 * @since 2020-05-16 21:15:03
 */
public class UnknownException extends AbstractExtensionBeanException {

    private static final long serialVersionUID = 8003339455039526608L;

    public UnknownException(String msg) {
        super(msg);
    }

    public UnknownException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
