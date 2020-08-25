package com.battleramp.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.battleramp.common.wrapper.PersonWrapper;

@Repository
public interface PersonRepository extends CrudRepository<PersonWrapper, String> {

	List<PersonWrapper> findByFirstname(String firstName);

}
