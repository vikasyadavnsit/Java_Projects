package org.api.wrapper.generic;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddressWrapper implements Serializable {
	private static final long serialVersionUID = 1824830202211090304L;
	private String street;
	private String city;
	private String state;
	private String zipcode;
}
