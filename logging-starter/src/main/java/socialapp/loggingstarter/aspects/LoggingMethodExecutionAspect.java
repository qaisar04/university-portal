package socialapp.loggingstarter.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;
import socialapp.loggingstarter.annotations.LoggableInfo;

/**
 * The {@code LoggingMethodExecutionAspect} class is an AspectJ aspect responsible for logging method executions
 * in the classes annotated with {@code @LoggableInfo}. It logs information about the start and end of method execution,
 * along with the execution time in milliseconds.
 *
 * <p>This aspect is designed to work in conjunction with the {@code @LoggableInfo} annotation, providing a convenient
 * way to log method execution details for the specified classes or methods.
 *
 * <p>Example usage:
 * <pre>
 * // Apply the @LoggableInfo annotation to classes or methods you want to log
 * {@literal @}LoggableInfo
 * public class ExampleClass {
 *     public void exampleMethod() {
 *         // code
 *     }
 * }
 * </pre>
 */
@Aspect
@Slf4j
public class LoggingMethodExecutionAspect {

    @Pointcut("(within(@socialapp.loggingstarter.annotations.LoggableInfo *) || execution(@socialapp.loggingstarter.annotations.LoggableInfo * *(..))) && execution(* *(..))")
    public void annotatedByLoggable() {
    }

    @Around("annotatedByLoggable()")
    public Object logMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
        var methodSignature = (MethodSignature) pjp.getSignature();
        var methodName = methodSignature.getName();
        var className = methodSignature.getDeclaringType().getSimpleName();

        log.info("Executing method {} in class {}", methodName, className);

        LoggableInfo loggableInfo = methodSignature.getMethod().getAnnotation(LoggableInfo.class);
        if (loggableInfo == null) {
            loggableInfo = methodSignature.getMethod().getDeclaringClass().getAnnotation(LoggableInfo.class);
        }

        String name = (loggableInfo != null) ? loggableInfo.name() : "";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var result = pjp.proceed();
        stopWatch.stop();

        log.info("Method {} in class {} completed in {} ms | {}", methodName, className, stopWatch.getTotalTimeMillis(), name);

        return result;
    }
}