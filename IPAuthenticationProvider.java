package com.itgi.portal.config.security;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.itgi.portal.affinity.controller.AffinityController;

@Component
public class IPAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(AffinityController.class);

	@Value("${whitelist.ips}")
	private Set<String> whitelistIps;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		String userIp = details.getRemoteAddress();
		if (!whitelistIps.contains(userIp)) {
			logger.error(userIp + " is an Unathorized User");
			throw new AccessDeniedException("Unauthorized User");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
