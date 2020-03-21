package org.api;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {

	Set<String> whitelist = new HashSet<String>();

	public CustomIpAuthenticationProvider() {
		whitelist.add("127.0.0.1");
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
		String userIp = details.getRemoteAddress();
		if (!whitelist.contains(userIp)) {
			throw new BadCredentialsException("Invalid IP Address");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
