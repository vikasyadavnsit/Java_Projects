package org.api.services.redis;

import org.api.repository.PersonRepository;
import org.api.wrapper.generic.PersonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private PersonRepository personRepository;

	public Iterable<PersonWrapper> getAllPerson() {
		return personRepository.findAll();
	}

	public PersonWrapper savePerson(PersonWrapper person) {
		PersonWrapper wrapper = personRepository.save(person);
		return wrapper;
	}

}
