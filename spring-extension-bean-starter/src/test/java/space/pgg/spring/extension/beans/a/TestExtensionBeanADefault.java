package space.pgg.spring.extension.beans.a;

import javax.annotation.Resource;

import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.beans.SomePlainBean;
import space.pgg.spring.extension.interfaces.A;

/**
 * default extension bean for {@link A}
 *
 * @author pgg
 * @since 2020-05-30 12:13:13
 */
@ExtensionBean(forInterface = A.class, isDefault = true)
public class TestExtensionBeanADefault extends AbstractExtensionBeanA {

    @Resource
    private SomePlainBean somePlainBean;

    @Override
    public Object giveMeSomething() {
        return somePlainBean;
    }

}
