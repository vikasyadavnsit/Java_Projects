package org.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private final AdminServerProperties adminServer;

	public SecuritySecureConfig(AdminServerProperties adminServer) {
		this.adminServer = adminServer;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests((authorizeRequest) -> authorizeRequest.anyRequest().permitAll()).csrf((csrf) -> csrf
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers(
						new AntPathRequestMatcher(this.adminServer.path("/instances"), HttpMethod.POST.toString()),
						new AntPathRequestMatcher(this.adminServer.path("/instances/*"), HttpMethod.DELETE.toString()),
						new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))));
	}

}