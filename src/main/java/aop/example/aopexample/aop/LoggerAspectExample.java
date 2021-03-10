package aop.example.aopexample.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspectExample {

    Logger log = LoggerFactory.getLogger(LoggerAspectExample.class);
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * this method will allow to implement the pointcut in every part of the project
     * * aop.example.aop.example.*.*.*(..)) will allow to put this method in all the project
     * .*.*.*(..) means : any package, any class, any method with a number of arguments
     * if we want for example to use it in a specific package we need to proceed like this:
     * * aop.example.aopexample.controller.UserController.getUsers(..)
     */
//    @Pointcut(value = "execution(* aop.example.aopexample.*.*.*(..))")
//    public void myPointCut() {
//
//    }


    /**
     * the second example is to build example by annotation and this annotation you can use it
     * in the method that you prefere , you need just to declare the annotation to the method
     * in our case this annotation is used in get user
     */
    @Pointcut(value = "@annotation(aop.example.aopexample.aop.PropagateSelectedAnnotation)")
    public void myPointCut() {

    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        Object o = proceedingJoinPoint.proceed();
        log.info("method invoked " + className + " : " + methodName + "() " + "arguments" + objectMapper.writeValueAsString(array));
        log.info(className + " : " + methodName + "() " + "response" + objectMapper.writeValueAsString(o));

        return o;
    }
}
