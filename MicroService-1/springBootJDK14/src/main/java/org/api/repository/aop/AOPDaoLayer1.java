package org.api.repository.aop;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AOPDaoLayer1 {

	List<String> getAllUsersNames();

	String getRandomUserName();

}
