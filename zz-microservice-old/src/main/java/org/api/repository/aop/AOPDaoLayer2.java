package org.api.repository.aop;

import java.util.List;

import org.api.wrapper.generic.PersonWrapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AOPDaoLayer2 {

	List<PersonWrapper> getAllUserDetails();

}
