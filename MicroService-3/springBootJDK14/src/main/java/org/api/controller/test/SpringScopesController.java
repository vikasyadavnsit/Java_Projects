package org.api.controller.test;

import org.api.services.PrototypeSpringScopesService;
import org.api.services.SingletonSpringScopesService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-scopes")
public class SpringScopesController {

	@Autowired
	private PrototypeSpringScopesService prototypeService;

	@Lookup
	PrototypeSpringScopesService getPrototypeWithLookupBean() {
		return null;
	}

	@Autowired
	private ObjectFactory<PrototypeSpringScopesService> prototypeBeanFactory;

	@Autowired
	private SingletonSpringScopesService singletonService;

	// Calling a protoype scope from singleton scope doesn't create new instance
	// every time
	@GetMapping("/prototype")
	public String prototype() {
		return prototypeService.prototype();
	}

	@GetMapping("/prototypeWithLookup")
	public String prototypeWithLookup() {
		return getPrototypeWithLookupBean().prototype();
	}

	@GetMapping("/prototypeWithObjectFactory")
	public String prototypeWithObjectFactory() {
		return prototypeBeanFactory.getObject().prototype();
	}

	@GetMapping("/singleton")
	public String singleton() {
		return singletonService.singleton();
	}

}
