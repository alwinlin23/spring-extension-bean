package space.pgg.spring.extension.error;

import org.springframework.beans.BeansException;

/**
 * Spring extension bean base exception
 *
 * @author pgg
 * @since 2020-05-16 20:35:44
 */
public abstract class AbstractExtensionBeanException extends BeansException {

    private static final long serialVersionUID = 840471426142334558L;

    public AbstractExtensionBeanException(String msg) {
        super(msg);
    }

    public AbstractExtensionBeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
