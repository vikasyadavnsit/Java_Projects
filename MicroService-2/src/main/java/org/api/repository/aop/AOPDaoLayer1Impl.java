package org.api.repository.aop;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class AOPDaoLayer1Impl implements AOPDaoLayer1 {

	static List<String> users = Arrays.asList("vikas", "arjun", "saml", "tisha");

	@Override
	public List<String> getAllUsersNames() {
		return users;
	}

	@Override
	public String getRandomUserName() {
		return users.get(new Random().nextInt(4));
	}

}
