package space.pgg.spring.extension.beans.a;

import space.pgg.spring.extension.beans.BaseBean;
import space.pgg.spring.extension.interfaces.A;

/**
 * abstract extension bean A
 *
 * @author pgg
 * @since 2020-05-30 12:30:45
 */
public abstract class AbstractExtensionBeanA extends BaseBean implements A {

    @Override
    public String sayHi(String name) {
        return saySomething(name);
    }

}
