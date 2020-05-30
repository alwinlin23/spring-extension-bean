package space.pgg.spring.extension.beans.b;

import javax.annotation.Resource;

import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.beans.BaseBean;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
import space.pgg.spring.extension.interfaces.B;

/**
 * test extension bean B
 *
 * @author pgg
 * @since 2020-05-30 12:45:19
 */
@ExtensionBean(forInterface = B.class, forCase = {Case.CASE_1, Case.CASE_2})
public class TestExtensionBeanB1 extends BaseBean implements B {

    @Resource
    private A testExtensionBeanA1;

    @Override
    public String sayBye(String name) {
        return saySomething(name);
    }

    @Override
    public A ownsTheA() {
        return testExtensionBeanA1;
    }

}
