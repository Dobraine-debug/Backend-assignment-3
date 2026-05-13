package se.yrgo.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.DecimalFormat;

@Aspect
@Component
public class PerformanceTimingAdvice {
    @Around("execution(* se.yrgo.dataaccess.*.*(..)) || execution(* se.yrgo.services.calls.*.*(..)) || execution(* se.yrgo.services.customers.*.*(..))" +
            "|| execution(* se.yrgo.services.diary.*.*(..))")
    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable{
        double startTime = System.nanoTime();
        try{
            Object value = method.proceed();
            return value;
        }
        finally {
            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;
            System.out.println("Time taken for the method " + method.getSignature().getName() + " from the class " + method.getSignature().getDeclaringTypeName() +
                    "took " + timeTaken/1000000 + " ms");
        }
    }
}
