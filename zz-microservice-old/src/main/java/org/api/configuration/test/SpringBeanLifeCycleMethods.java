package org.api.configuration.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CustomBean1 implements InitializingBean, DisposableBean {
	public void initMethod() {
		log.info("Inside CustomBean1 Init() Method");
	}

	public void destroyMethod() {
		log.info("Inside CustomBean1 Destroy() Method");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Inside CustomBean1 afterPropertiesSet() Method ");
	}

	@Override
	public void destroy() throws Exception {
		log.info("Inside CustomBean1 Destroy() Method of Disposable Bean");
	}

	@PostConstruct
	public void afterConstruction() {
		log.info("Inside CustomBean1 afterConstruction() Method");
	}

	@PreDestroy
	public void beforeDestruction() {
		log.info("Inside CustomBean1 beforeDestruction() Method");
	}
};

class CustomBean2 {
};

@Configuration
public class SpringBeanLifeCycleMethods {

	@Bean(name = "customBean1", initMethod = "initMethod", destroyMethod = "destroyMethod")
	public CustomBean1 bean1() {
		return new CustomBean1();
	}

	@Bean(name = "customBean2")
	public CustomBean2 bean2() {
		return new CustomBean2();
	}

}
