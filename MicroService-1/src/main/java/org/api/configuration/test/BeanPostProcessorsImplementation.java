package org.api.configuration.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BeanPostProcessorsImplementation implements BeanPostProcessor {

	public BeanPostProcessorsImplementation() {
		log.info("BeanPostProcessorsImplementation Instance is Created");
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof CustomBean1) {
			log.info("Inside postProcessAfterInitialization() Method for Bean : " + beanName);
		}
		return bean;
	}
	// This method will be called for each and every bean before its initalization

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof CustomBean1) {
			log.info("Inside postProcessBeforeInitialization() Method for Bean : " + beanName);
		}
		return bean;
	}
	// This method will be called for each and every bean after its initalization

}
