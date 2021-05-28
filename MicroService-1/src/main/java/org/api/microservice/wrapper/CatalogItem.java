package org.api.microservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CatalogItem {

	private String name;
	private String description;
	private int rating;
}
