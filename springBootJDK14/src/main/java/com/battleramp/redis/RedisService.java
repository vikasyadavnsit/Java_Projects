package com.battleramp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battleramp.common.repository.PersonRepository;
import com.battleramp.common.wrapper.PersonWrapper;

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
