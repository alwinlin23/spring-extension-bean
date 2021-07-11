package space.pgg.spring.extension.exception;

import org.springframework.beans.BeansException;

/**
 * Spring extension bean base exception
 *
 * @author pgg
 * @since 2020-05-16 20:35:44
 */
public abstract class AbstractExtensionBeanException extends BeansException {

    private static final long serialVersionUID = 1L;

    public AbstractExtensionBeanException(String msg) {
        super(msg);
    }

    public AbstractExtensionBeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
