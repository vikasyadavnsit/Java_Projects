package org.api.repository.transaction;

import java.util.List;

import javax.persistence.EntityManager;

import org.api.wrapper.generic.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPATransaction {

	@Autowired
	private EntityManager entityManger;
	
	public List<UserWrapper> fetchAllUsers(){
		//entityManger.
		return null;
	}
	
}
