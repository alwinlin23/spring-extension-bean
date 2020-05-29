package space.pgg.spring.extension.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import space.pgg.spring.extension.annotation.EnableExtensionBean;

/**
 * Test Application
 *
 * @author pgg
 * @since 2020-05-30 01:32:49
 */
@SpringBootApplication
@EnableExtensionBean(basePackages = "space.pgg.spring.extension")
public class TestApplication {
}
