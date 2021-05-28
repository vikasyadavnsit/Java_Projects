package org.api.configuration.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class MBCI1 {
}

@Component
class MBCI2 {
}

@Component
public class MultipleBeanConstructorInjection {

	private MBCI1 obj;

	@Autowired(required = false)
	MultipleBeanConstructorInjection(MBCI1 obj1) {
		System.out.println("MBCI1(obj1) : Hashcode " + obj1.hashCode());
		this.obj = obj1;
	}

	@Autowired(required = false)
	MultipleBeanConstructorInjection(MBCI1 obj1, MBCI2 obj2) {
		System.out.println("MBCI1(obj1,obj2) : Hashcode " + obj1.hashCode());
		if (this.obj != null && this.obj.hashCode() == obj1.hashCode()) {
			System.out.println("Both Injected bean of type MBCI1 are same.");
		} else if (this.obj != null) {
			System.out.println("Both Injected bean of type MBCI1 are different.");
		}
	}

}
