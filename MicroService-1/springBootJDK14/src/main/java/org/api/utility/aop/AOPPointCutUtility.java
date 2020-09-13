package org.api.utility.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AOPPointCutUtility {

	@Pointcut("execution(* org.api.services.aop..*.*(..))")
	public void allServicePackages() {
	}

	@Pointcut("execution(* org.api.repository.aop..*.*(..))")
	public void allRepositoryPackages() {
	}

	@Pointcut("allServicePackages() || allRepositoryPackages()")
	public void allAOPSeriveAndDaoPackages() {
	}

}
