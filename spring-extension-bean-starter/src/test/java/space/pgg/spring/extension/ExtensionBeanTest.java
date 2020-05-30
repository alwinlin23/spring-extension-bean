package space.pgg.spring.extension;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.pgg.spring.extension.application.TestApplication;
import space.pgg.spring.extension.beans.SomePlainBean;
import space.pgg.spring.extension.beans.a.TestExtensionBeanA1;
import space.pgg.spring.extension.beans.a.TestExtensionBeanADefault;
import space.pgg.spring.extension.beans.complex.CompoundExtensionBean;
import space.pgg.spring.extension.constant.Constants.Case;
import space.pgg.spring.extension.interfaces.A;
import space.pgg.spring.extension.interfaces.B;
import space.pgg.spring.extension.route.ExtensionPoint;
import space.pgg.spring.extension.util.JacksonUtil;

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
    private SomePlainBean somePlainBean;
    @Resource
    private TestExtensionBeanA1 testExtensionBeanA1;
    @Resource
    private TestExtensionBeanADefault testExtensionBeanADefault;
    @Resource
    private CompoundExtensionBean compoundExtensionBean;

    @Test
    public void simpleUsage() {
        String output = ExtensionPoint
            .on(A.class)
            .of(Case.CASE_1)
            .sayHi(TO_PGG);
        System.out.println(output);
        assertThat(output).isEqualTo(testExtensionBeanA1.sayHi(TO_PGG));
    }

    @Test
    public void defaultExtensionBean() {
        String output = ExtensionPoint
            .on(A.class)
            .of("SOME MAGIC CASE NAME THAT EVEN DESERVE NO VARIABLE")
            .sayHi(TO_PGG);
        System.out.println(output);
        assertThat(output).isEqualTo(testExtensionBeanADefault.sayHi(TO_PGG));
    }

    @Test
    public void extensionBeanShouldWorkAsNormalSpringBean_1() {
        SomePlainBean somePlainBean = (SomePlainBean)ExtensionPoint
            .on(A.class)
            .of(Case.CASE_1)
            .giveMeSomething();
        System.out.println(JacksonUtil.toJson(somePlainBean));
        assertThat(somePlainBean == this.somePlainBean).isTrue();
    }

    @Test
    public void extensionBeanShouldWorkAsNormalSpringBean_2() {
        A anA = ExtensionPoint
            .on(B.class)
            .of(Case.CASE_1)
            .ownsTheA();
        System.out.println(JacksonUtil.toJson(anA));
        assertThat(anA == this.testExtensionBeanA1).isTrue();
    }

    @Test
    public void someCasesCouldRouteToTheSameImplementation() {
        String outputForCase1 = ExtensionPoint
            .on(B.class)
            .of(Case.CASE_1)
            .sayBye(TO_PGG);
        System.out.println(outputForCase1);

        String outputForCase2 = ExtensionPoint
            .on(B.class)
            .of(Case.CASE_2)
            .sayBye(TO_PGG);
        System.out.println(outputForCase2);

        assertThat(outputForCase1).isEqualTo(outputForCase2);
    }

    @Test
    public void compoundExtensionBeanCouldBeTheImplementationOfSeveralInterface() {
        String outputA = ExtensionPoint
            .on(A.class)
            .of(Case.CASE_COMPOUND)
            .sayHi(TO_PGG);
        System.out.println(outputA);

        String outputB = ExtensionPoint
            .on(B.class)
            .of(Case.CASE_COMPOUND)
            .sayBye(TO_PGG);
        System.out.println(outputB);

        String outputCompoundA = compoundExtensionBean.sayHi(TO_PGG);
        String outputCompoundB = compoundExtensionBean.sayBye(TO_PGG);
        assertThat(outputA).isEqualTo(outputCompoundA);
        assertThat(outputB).isEqualTo(outputCompoundB);
    }
}
