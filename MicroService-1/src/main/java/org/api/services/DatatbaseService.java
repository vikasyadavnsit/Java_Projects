package org.api.services;

import java.util.List;

import org.api.wrapper.generic.TestWrapper;
import org.springframework.stereotype.Service;

@Service
public interface DatatbaseService {

	public List<TestWrapper> getAllData();
}
