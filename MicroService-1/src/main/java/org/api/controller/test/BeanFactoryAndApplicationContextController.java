package org.api.controller.test;

import java.util.Date;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ioc")
public class BeanFactoryAndApplicationContextController {

	@GetMapping("/beanFactory")
	public String beanFactory() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
		BeanFactoryBean beanFactoryBean = (BeanFactoryBean) factory.getBean("beanFactory");
		return beanFactoryBean != null ? "Bean Created" : "Bean Not Created";
	}

	@GetMapping("/applicationContext")
	public String applicationContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ApplicationContextBean applicationContextBean = (ApplicationContextBean) context
				.getBean("applicationContextBean");
		((ClassPathXmlApplicationContext) context).close();
		return applicationContextBean != null ? "Bean Created" : "Bean Not Created";
	}

}

@Slf4j
class BeanFactoryBean {
	public void init() {
		log.info(this.getClass() + " bean created at " + new Date().toString());
	}
};

@Slf4j
class ApplicationContextBean {
	public void init() {
		log.info(this.getClass() + " bean created at " + new Date().toString());
	}

};
