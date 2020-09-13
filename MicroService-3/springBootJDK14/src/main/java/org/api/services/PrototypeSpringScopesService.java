package org.api.services;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope("prototype")
public class PrototypeSpringScopesService {

	private Date createdAt;

	public PrototypeSpringScopesService() {
		this.createdAt = new Date();
	}

	public String prototype() {
		String str = "Inside PrototypeSpringScopesService : prototype() Method : Created at "
				+ this.createdAt.toString();
		log.info(str);
		return str;
	}
}
