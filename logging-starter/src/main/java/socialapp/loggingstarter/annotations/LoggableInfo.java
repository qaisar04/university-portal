package socialapp.loggingstarter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code LoggableInfo} annotation is used to mark classes or methods that should be logged
 * for method execution information. When applied at the class level, all methods within the class
 * will be considered for logging. When applied at the method level, only the annotated method
 * will be logged.
 *
 * <p>This annotation is typically used in conjunction with the {@code LoggingMethodExecutionAspect}
 * aspect to log method executions for the specified classes or methods.
 *
 * <p>Example usage:
 * <pre>
 * // Apply at the class level to log all methods in the class
 * {@literal @}LoggableInfo
 * public class ExampleClass {
 *     // Class content
 * }
 *
 * // Apply at the method level to log a specific method
 * {@literal @}LoggableInfo
 * public void exampleMethod() {
 *     // Method content
 * }
 * </pre>
 *
 * <p>The aspect configured with this annotation will log information about the start and end
 * of method execution, along with the execution time in milliseconds.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LoggableInfo {
    String name() default "";
}
