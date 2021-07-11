package space.pgg.spring.extension;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.pgg.spring.extension.application.TestApplication;
import space.pgg.spring.extension.beans.a.TestExtensionBeanA1;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
import space.pgg.spring.extension.route.ExtensionPoint;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author pgg
 * @since 2020-06-14 00:38:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class Demo {

    public static final String TO_PGG = "PGG";

    @Resource
    private TestExtensionBeanA1 testExtensionBeanA1;

    @Test
    public void test() {

    }
}
