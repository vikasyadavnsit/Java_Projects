package org.api;

import org.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableScheduling
@EnableRetry
@EnableCaching
@EnableAspectJAutoProxy
@EnableJdbcRepositories(basePackageClasses = UserRepository.class)
public class BootApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("######## Inside main() method #######");
		}
		SpringApplication.run(BootApplication.class, args);
	}
}
