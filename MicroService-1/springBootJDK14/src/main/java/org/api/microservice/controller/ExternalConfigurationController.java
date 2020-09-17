package org.api.microservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/external-configuration")
public class ExternalConfigurationController {

	@Value("${app.external.config.dummy.description:default value if key doesn't exist}")
	private String dummyDescription;

	@Value("static value")
	private String staticValue;

	@Value("${my.list.value}")
	private List<String> listValues;

	@Value("#{${db.connection}}")
	private Map<String, String> dbValues;

	@Autowired
	private DBSettingConfigurationProperties dbSettings;
	
	@Autowired
	private Environment env;

	@GetMapping("/application-properties")
	public String applicationProperites() {
		return dummyDescription + staticValue + listValues + dbValues + dbSettings;
	}

	@GetMapping("/evndetails")
	public String environmentDetails() {
		return env.getActiveProfiles().toString();
	}
	
}

