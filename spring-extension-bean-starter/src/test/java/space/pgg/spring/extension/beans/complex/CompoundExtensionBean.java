package space.pgg.spring.extension.beans.complex;

import javax.annotation.Resource;

import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.annotation.ExtensionBeans;
import space.pgg.spring.extension.beans.BaseBean;
import space.pgg.spring.extension.beans.SomePlainBean;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
import space.pgg.spring.extension.interfaces.B;

/**
 * compound extension bean that implements both interface A and B
 *
 * @author pgg
 * @since 2020-05-30 13:07:36
 */
@ExtensionBeans({@ExtensionBean(forInterface = A.class, forCase = Case.CASE_COMPOUND),
    @ExtensionBean(forInterface = B.class, forCase = Case.CASE_COMPOUND)})
public class CompoundExtensionBean extends BaseBean implements A, B {

    @Resource
    private SomePlainBean somePlainBean;
    @Resource
    private A testExtensionBeanADefault;

    @Override
    public String sayHi(String name) {
        return saySomething(name);
    }

    @Override
    public Object giveMeSomething() {
        return somePlainBean;
    }

    @Override
    public String sayBye(String name) {
        return saySomething(name);
    }

    @Override
    public A ownsTheA() {
        return testExtensionBeanADefault;
    }
}
