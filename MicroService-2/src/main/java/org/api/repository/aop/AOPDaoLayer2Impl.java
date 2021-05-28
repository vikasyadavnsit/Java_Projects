package org.api.repository.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.api.wrapper.generic.AddressWrapper;
import org.api.wrapper.generic.PersonWrapper;
import org.springframework.stereotype.Repository;

@Repository
public class AOPDaoLayer2Impl implements AOPDaoLayer2 {

	static List<PersonWrapper> userDetails;

	@PostConstruct
	void crateUserDetailsList() {
		userDetails = new ArrayList<>();
		IntStream.range(110059, 110070)
				.forEach(x -> userDetails.add(PersonWrapper.builder().id(String.valueOf(x - 110058))
						.firstname(UUID.randomUUID().toString()).lastname(UUID.randomUUID().toString())
						.address(AddressWrapper.builder().zipcode(String.valueOf(x)).build()).build()));
	}

	@Override
	public List<PersonWrapper> getAllUserDetails() {
		return userDetails;
	}

}
