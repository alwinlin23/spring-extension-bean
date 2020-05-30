package space.pgg.spring.extension.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * some test bean
 *
 * @author pgg
 * @since 2020-05-30 01:33:50
 */
@Data
@Component
public class SomePlainBean {

    private String name = "SomePlainBean";

}
