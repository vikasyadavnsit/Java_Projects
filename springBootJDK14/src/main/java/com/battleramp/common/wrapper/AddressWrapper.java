package com.battleramp.common.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddressWrapper {

	private String street;
	private String city;
	private String state;
	private String zipcode;
}
