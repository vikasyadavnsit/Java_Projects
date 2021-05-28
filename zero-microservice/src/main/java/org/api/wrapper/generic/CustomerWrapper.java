package org.api.wrapper.generic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerWrapper extends RepresentationModel<CustomerWrapper> implements Serializable {
	private static final long serialVersionUID = -9075713622496175427L;
	private long customerID;
	private String name;
	private Date age;
	private List<OrdersWrapper> orders;
}
