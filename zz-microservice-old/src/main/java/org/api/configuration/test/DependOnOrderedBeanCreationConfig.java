package org.api.configuration.test;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DependOnOrderedBeanCreationConfig {

	@Bean("bean1")
	@DependsOn({ "bean2" })
	public Bean1 getBean1() {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
		} catch (InterruptedException e) {
			log.error("Exception Creating Bean1 : OrderedBeanCreationConfig ");
		}
		return new Bean1();
	}
	// Using @DependsOn (can have multiple dependent beans) annotaion make sure
	// that, dependent instances are created first

	@Bean("bean2")
	public Bean2 getBean2() {
		return new Bean2();
	}
}

@Slf4j
class Bean1 implements InitializingBean {
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Bean1 Instantiated at " + new Date().toString());
	}
};

@Slf4j
class Bean2 {
	@PostConstruct
	public void afterBeanConstrunction() {
		log.info("Bean2 Instantiated at " + new Date().toString());
	}
};
