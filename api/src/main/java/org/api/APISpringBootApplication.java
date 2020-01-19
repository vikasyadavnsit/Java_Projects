package org.api;

import org.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@SpringBootApplication
//@EnableJdbcRepositories(basePackageClasses = UserRepository.class)
public class APISpringBootApplication {

	private static Logger logger = LoggerFactory.getLogger(APISpringBootApplication.class);

	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("######## Inside main() method #######");
		}
		SpringApplication.run(APISpringBootApplication.class, args);
	}

// ############### SECURITY SETTINGS FOR ACUTUATOR #####################	
//	@Configuration
//	public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
//			http.authorizeRequests().antMatchers("/actuator", "/actuator/**").authenticated().and().formLogin().and()
//					.httpBasic();
//		}
//	}
}
