package socialapp.loggingstarter.aspects;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import socialapp.loggingstarter.annotations.LoggableInfo;
import socialapp.loggingstarter.annotations.LoggableTime;

import java.util.concurrent.TimeUnit;

/**
 * The {@code LoggingTimeExecutionAspect} class is an AspectJ aspect responsible for logging method execution time
 * in the classes or methods annotated with {@code @LoggableTime}. It logs information about the execution time
 * in milliseconds.
 *
 * <p>This aspect is designed to work in conjunction with the {@code @LoggableTime} annotation, providing a convenient
 * way to log method execution time for the specified classes or methods.
 *
 * <p>Example usage:
 * <pre>
 * // Apply the @LoggableTime annotation to classes or methods you want to log execution time
 * {@literal @}LoggableTime
 * public class ExampleClass {
 *     {@literal @}LoggableTime
 *     public void exampleMethod() {
 *         // code
 *     }
 * }
 * </pre>
 */
@Aspect
@Slf4j
public class LoggingTimeExecutionAspect {

    @Pointcut("(within(@socialapp.loggingstarter.annotations.LoggableTime *) || execution(@socialapp.loggingstarter.annotations.LoggableTime * *(..))) && execution(* *(..))")
    public void annotatedByLoggableTime() {
    }

    @Around("annotatedByLoggableTime()")
    public Object logMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        final var stopWatch = new StopWatch();

        stopWatch.start();
        var result = pjp.proceed();
        stopWatch.stop();

        LoggableTime loggableTime = methodSignature.getMethod().getAnnotation(LoggableTime.class);
        if (loggableTime == null) {
            loggableTime = methodSignature.getMethod().getDeclaringClass().getAnnotation(LoggableTime.class);
        }

        String name = (loggableTime != null) ? loggableTime.name() : "";

        log.info(
                "%s.%s :: %s ms | %s".formatted(
                        methodSignature.getDeclaringType().getSimpleName(), // class name
                        methodSignature.getName(), // method name
                        stopWatch.getTime(TimeUnit.MILLISECONDS), // execution time in milliseconds
                        name
                )
        );

        return result;
    }
}

