package org.api.services.aop;

import java.util.List;

import org.api.repository.aop.AOPDaoLayer1;
import org.api.repository.aop.AOPDaoLayer2;
import org.api.wrapper.generic.PersonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AOPServiceImpl implements AOPService {

	@Autowired
	private AOPDaoLayer1 aopLayer1;

	@Autowired
	private AOPDaoLayer2 aopLayer2;

	@Override
	public List<String> getAllUsersNames() {
		return aopLayer1.getAllUsersNames();
	}

	@Override
	public String getRandomUserName() {
		return aopLayer1.getRandomUserName();
	}

	@Override
	public List<PersonWrapper> getAllUserDetails() {
		return aopLayer2.getAllUserDetails();
	}
}
