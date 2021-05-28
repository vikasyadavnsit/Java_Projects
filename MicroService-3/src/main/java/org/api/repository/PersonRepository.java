package org.api.repository;

import java.util.List;

import org.api.wrapper.generic.PersonWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonWrapper, String> {

	List<PersonWrapper> findByFirstname(String firstName);

}
