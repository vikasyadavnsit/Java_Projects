package org.battleramp.service;

import java.util.List;

import org.battleramp.repository.DemoDataRepository;
import org.battleramp.wrapper.TestWrapper;
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
