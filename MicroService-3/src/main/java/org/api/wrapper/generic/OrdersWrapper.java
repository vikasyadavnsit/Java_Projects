package org.api.wrapper.generic;

import java.io.Serializable;

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
public class OrdersWrapper extends RepresentationModel<OrdersWrapper> implements Serializable {
	private static final long serialVersionUID = 3012507536002490822L;
	private long orderID;
	private String item;
	private int cost;
	private int quantity;
}
