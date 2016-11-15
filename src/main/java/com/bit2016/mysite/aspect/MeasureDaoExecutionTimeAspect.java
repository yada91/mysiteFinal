package com.bit2016.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureDaoExecutionTimeAspect {

	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..))" )
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();

		Object result = pjp.proceed();

		// after
		sw.stop();
		System.out.println("[excution" + pjp.getTarget().getClass().getName() + " " + pjp.getSignature().getName()
				+ "] " + sw.getTotalTimeMillis());
		return result;
	}
}
