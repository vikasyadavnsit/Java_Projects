package org.api.utility.aop;

import java.time.Duration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class LoggingAOPUtility {

	@Before(value = "AOPPointCutUtility.allAOPSeriveAndDaoPackages()")
	public void beforeMethodCall(JoinPoint joinPoint) {
		log.info("Inside " + joinPoint.getTarget().getClass() + " : " + joinPoint.getSignature().getName());
	}

	// Executes only when a method executes successfully
	@AfterReturning(value = "AOPPointCutUtility.allAOPSeriveAndDaoPackages()", returning = "result")
	public void afterRetuningMethodCall(JoinPoint joinPoint, Object result) {
		log.info("Executed Successfully " + joinPoint.getTarget().getClass() + " : "
				+ joinPoint.getSignature().getName() + " with : " + result.toString());
	}

	// Executes when method throws exception
	@AfterThrowing(value = "AOPPointCutUtility.allAOPSeriveAndDaoPackages()")
	public void afterThrowingException(JoinPoint joinPoint) {
		log.error("Exception Inside " + joinPoint.getTarget().getClass() + " : " + joinPoint.getSignature().getName());
	}

	// We need to return the result after proceed()
	@Around(value = "AOPPointCutUtility.allServicePackages()")
	public Object totalExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		log.info("Total Execution Time Inside " + proceedingJoinPoint.getTarget().getClass() + " : "
				+ proceedingJoinPoint.getSignature().getName() + " is : "
				+ Duration.ofMillis(stopWatch.getTotalTimeMillis()));
		return result;
	}

	// It will be executed in two situations - when a method executes successfully
	// or it throws an exception
//	@After("AOPPointCutUtility.allServicePackages()")
//	public void afterMethodCall(JoinPoint joinPoint) {
//		log.info("Inside " + joinPoint.getTarget().getClass() + " : " + joinPoint.getSignature().getName());
//	}

}
