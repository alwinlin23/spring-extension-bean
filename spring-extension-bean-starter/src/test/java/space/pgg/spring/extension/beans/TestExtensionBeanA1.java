package space.pgg.spring.extension.beans;

import javax.annotation.Resource;

import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.TestInterfaceA;
import space.pgg.spring.extension.util.RuntimeUtils;

/**
 * @author pgg
 * @since 2020-05-30 01:40:31
 */
@ExtensionBean(forInterface = TestInterfaceA.class, forCase = Case.CASE_1)
public class TestExtensionBeanA1 implements TestInterfaceA {

    @Resource
    private SomePlainBean somePlainBean;

    @Override
    public String sayHi(String name) {
        return String.format("%s : %s", RuntimeUtils.getCurrentMethodName(), name);
    }

    @Override
    public Object getSomeThing() {
        return somePlainBean;
    }

}
