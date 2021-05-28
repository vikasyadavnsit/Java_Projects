package org.api.services.aop;

import java.util.List;

import org.api.wrapper.generic.PersonWrapper;
import org.springframework.stereotype.Service;

@Service
public interface AOPService {

	List<String> getAllUsersNames();

	String getRandomUserName();

	List<PersonWrapper> getAllUserDetails();

}
