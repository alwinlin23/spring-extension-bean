package space.pgg.spring.extension.beans;

import space.pgg.spring.extension.util.RuntimeUtil;

/**
 * base test bean
 *
 * @author pgg
 * @since 2020-05-30 12:29:40
 */
public class BaseBean {

    public String saySomething(String name) {
        return String.format("%s : %s", RuntimeUtil.getInvokeMethodName(), name);
    }

}
