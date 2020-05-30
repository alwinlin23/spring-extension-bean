package space.pgg.spring.extension.beans.a;

import javax.annotation.Resource;

import lombok.Data;
import space.pgg.spring.extension.annotation.ExtensionBean;
import space.pgg.spring.extension.beans.SomePlainBean;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
import space.pgg.spring.extension.util.RuntimeUtil;

/**
 * @author pgg
 * @since 2020-05-30 01:40:31
 */
@ExtensionBean(forInterface = A.class, forCase = Case.CASE_1)
public class TestExtensionBeanA1 implements A {

    @Resource
    private SomePlainBean somePlainBean;

    @Override
    public String sayHi(String name) {
        return String.format("%s : %s", RuntimeUtil.getCurrentMethodName(), name);
    }

    @Override
    public Object giveMeSomething() {
        return somePlainBean;
    }

}
