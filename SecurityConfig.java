package com.itgi.portal.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.itgi.portal.common.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${breakin.api.url}")
	private String breakinApiUrl;

	@Value("${breakin.api.roles}")
	private String[] breakinApiRoles;

	@Value("${mobile.api.url}")
	private String mobileApiUrl;

	@Value("${mobile.api.roles}")
	private String[] mobileApiRoles;

	@Value("${itgi.api.url}")
	private String itgiApiUrl;

	@Value("${itgi.api.roles}")
	private String[] itgiApiRoles;

	@Value("${superadmin.url}")
	private String superAdminUrl;

	@Value("${superadmin.roles}")
	private String[] superAdminRole;

	@Autowired
	private PortalCorsFilter portalCorsFilter;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private IPAuthenticationProvider authenticationProvider;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImp();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
		auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				// Disable CSRF protection since tokens are immune to it
				.csrf().disable().httpBasic()
				// this is a state less application, disable sessions
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// Authorization
				.authorizeRequests().antMatchers(breakinApiUrl).hasAnyAuthority(breakinApiRoles)
				.antMatchers(mobileApiUrl).hasAnyAuthority(mobileApiRoles).antMatchers(itgiApiUrl)
				.hasAnyAuthority(itgiApiRoles).antMatchers(superAdminUrl).hasAnyAuthority(superAdminRole).anyRequest()
				.authenticated().and().formLogin()
				// If the user is not authenticated, returns 401
				.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				// Custom filter for CORS
				.and().addFilterBefore(portalCorsFilter, ChannelProcessingFilter.class);
	}

}
