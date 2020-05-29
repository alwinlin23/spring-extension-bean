package space.pgg.spring.extension;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.pgg.spring.extension.application.TestApplication;
import space.pgg.spring.extension.beans.TestExtensionBeanA1;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.TestInterfaceA;
import space.pgg.spring.extension.route.ExtensionPoint;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test
 *
 * @author pgg
 * @since 2020-05-30 01:45:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ExtensionBeanTest {

    @Resource
    private TestExtensionBeanA1 testExtensionBeanA1;

    @Test
    public void simpleUsage() {
        String name = "pgg";
        String output = ExtensionPoint
            .on(TestInterfaceA.class)
            .of(Case.CASE_1)
            .sayHi(name);
        assertThat(output).isEqualTo(testExtensionBeanA1.sayHi(name));
    }
}
