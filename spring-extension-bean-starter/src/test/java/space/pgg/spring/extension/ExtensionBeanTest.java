package space.pgg.spring.extension;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.pgg.spring.extension.application.TestApplication;
import space.pgg.spring.extension.beans.a.TestExtensionBeanA1;
import space.pgg.spring.extension.beans.a.TestExtensionBeanADefault;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
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

    public static final String TO_PGG = "PGG";

    @Resource
    private TestExtensionBeanA1 testExtensionBeanA1;
    @Resource
    private TestExtensionBeanADefault testExtensionBeanADefault;

    @Test
    public void simpleUsage() {
        String output = ExtensionPoint
            .on(A.class)
            .of(Case.CASE_1)
            .sayHi(TO_PGG);
        assertThat(output).isEqualTo(testExtensionBeanA1.sayHi(TO_PGG));
    }

    @Test
    public void defaultExtensionBean() {
        String output = ExtensionPoint
            .on(A.class)
            .of("SOME MAGIC CASE NAME THAT EVEN HAS NO VARIABLE")
            .sayHi(TO_PGG);
        assertThat(output).isEqualTo(testExtensionBeanADefault.sayHi(TO_PGG));
    }
}
