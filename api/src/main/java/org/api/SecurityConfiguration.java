package org.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

// H2 DATABASE 
//		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
//				.withUser(User.withUsername("user").password("user").roles("USER"))
//				.withUser(User.withUsername("admin").password("admin").roles("ADMIN"));

// IN MEMORY AUTHENTICATION
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and().withUser("user")
//				.password("user").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/", "/static/**").permitAll().and().formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
