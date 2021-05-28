package org.api.configuration.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("BeanInterfaceClass1")
@Primary
class BeanInterfaceClass1 implements BeanInterface {

	@Override
	public void foo() {
	}
}

@Component("BeanInterfaceClass2")
class BeanInterfaceClass2 implements BeanInterface {

	@Override
	public void foo() {
	}
}

@Component
public class QualifierBeanAnnotation {

	// @Primary annotation inject the bean marked
	@Autowired
	private BeanInterface bean1;

	@Autowired
	@Qualifier(value = "BeanInterfaceClass2")
	private BeanInterface bean2;

	@PostConstruct
	private void beansCreated() {
		System.out.println("Bean 1 Created");
	}
}
