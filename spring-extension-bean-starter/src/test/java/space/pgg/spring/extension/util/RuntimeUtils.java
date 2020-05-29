package space.pgg.spring.extension.util;

/**
 * Runtime utilities
 *
 * @author pgg
 * @since 2020-05-30 01:38:54
 */
public class RuntimeUtils {

    public static String getCurrentMethodName() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        return String.format("%s.%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName());
    }

}
