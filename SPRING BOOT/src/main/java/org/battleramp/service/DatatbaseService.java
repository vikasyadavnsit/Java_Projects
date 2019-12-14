package org.battleramp.service;

import java.util.List;

import org.battleramp.wrapper.TestWrapper;
import org.springframework.stereotype.Service;

@Service
public interface DatatbaseService {

	public List<TestWrapper> getAllData();
}
