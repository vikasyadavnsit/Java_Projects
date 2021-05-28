package org.api.services;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//or @Scope("singleton")
public class SingletonSpringScopesService {

	private Date createdAt;

	public SingletonSpringScopesService() {
		this.createdAt = new Date();
	}

	public String singleton() {
		String str = "Inside SingletonSpringScopesService : singleton() Method : Created at "
				+ this.createdAt.toString();
		log.info(str);
		return str;
	}
}
