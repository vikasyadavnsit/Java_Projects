package org.api.services;

import java.util.List;

import org.api.repository.DemoDataRepository;
import org.api.wrapper.generic.TestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseServiceImpl implements DatatbaseService {

	@Autowired
	private DemoDataRepository demoDataRepository;

	@Override
	public List<TestWrapper> getAllData() {
		return demoDataRepository.getAllData();
	}

}
