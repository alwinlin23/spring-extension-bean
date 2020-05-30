package space.pgg.spring.extension.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * jackson utilities
 *
 * @author pgg
 * @since 2020-05-30 12:58:57
 */
public class JacksonUtil {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String toJson(Object object) {
        return MAPPER.writeValueAsString(object);
    }
}
