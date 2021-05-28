package org.api.configuration.test;

import java.util.List;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

interface Rating {
	int getRating();
}

@Component
@Order(2)
class Good implements Rating {

	@Override
	public int getRating() {
		return 2;
	}
}

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
class Average implements Rating {

	@Override
	public int getRating() {
		return 3;
	}
}

@Component
@Order(1)
class Excellent implements Rating {

	@Override
	public int getRating() {
		return 1;
	}
}

@Configuration
@Getter
@Setter
@Slf4j
@ToString
public class OrderedBeanCreationConfig {

	private List<Rating> ratings;

	OrderedBeanCreationConfig(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@PostConstruct
	public void printBeansOrder() {
		StringJoiner joiner = new StringJoiner(",");
		ratings.forEach(x -> joiner.add(String.valueOf(x.getRating())));
		log.info("Beans Ordered is as follows : " + joiner.toString());
	}
}
