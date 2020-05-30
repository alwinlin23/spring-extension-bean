package space.pgg.spring.extension.util;

/**
 * Runtime utilities
 *
 * @author pgg
 * @since 2020-05-30 01:38:54
 */
public class RuntimeUtil {

    public static final int CURRENT_METHOD_STACK_INDEX = 3;
    public static final int INVOKE_METHOD_STACK_INDEX = 4;

    public static String getCurrentMethodName() {
        return getMethodNameFromStack(CURRENT_METHOD_STACK_INDEX);
    }

    private static String getMethodNameFromStack(int stackIndex) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[stackIndex];
        return String.format("%s.%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName());
    }

    public static String getInvokeMethodName() {
        return getMethodNameFromStack(INVOKE_METHOD_STACK_INDEX);
    }

}
