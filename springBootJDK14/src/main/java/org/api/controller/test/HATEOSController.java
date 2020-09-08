package org.api.controller.test;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.api.services.HATEOSService;
import org.api.wrapper.generic.CustomerWrapper;
import org.api.wrapper.generic.OrdersWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hateos")
public class HATEOSController {

	@Autowired
	private HATEOSService hateosService;

	@GetMapping("/getCustomer/{customerID}")
	public EntityModel<CustomerWrapper> getCustomer(@PathVariable long customerID) {
		CustomerWrapper wrapper = hateosService.getCustomer(customerID);
		List<OrdersWrapper> orders = wrapper.getOrders();

		for (final OrdersWrapper order : orders) {
			Link selfLink = linkTo(methodOn(HATEOSController.class).getOrder(customerID, order.getOrderID()))
					.withSelfRel();
			order.removeLinks();
			order.add(selfLink);
		}

		Link selfLink = linkTo(methodOn(HATEOSController.class).getCustomer(customerID)).withSelfRel();
		wrapper.removeLinks();
		wrapper.add(selfLink);
		return EntityModel.of(wrapper);
	}

	@GetMapping("/getAllCustomers")
	public CollectionModel<CustomerWrapper> getAllCustomers() {
		List<CustomerWrapper> customers = hateosService.getAllCustomers();

		for (final CustomerWrapper wrapper : customers) {

			Link childLink = linkTo(methodOn(HATEOSController.class).getCustomer(wrapper.getCustomerID()))
					.withSelfRel();
			wrapper.removeLinks();
			wrapper.add(childLink);

			List<OrdersWrapper> orders = wrapper.getOrders();
			for (final OrdersWrapper order : orders) {
				Link selfLink = linkTo(
						methodOn(HATEOSController.class).getOrder(wrapper.getCustomerID(), order.getOrderID()))
								.withSelfRel();
				order.removeLinks();
				order.add(selfLink);
			}
		}

		Link selfLink = linkTo(methodOn(HATEOSController.class).getAllCustomers()).withSelfRel();
		return CollectionModel.of(customers, selfLink);
	}

	@GetMapping("/getCustomer/{customerID}/getOrder/{orderID}")
	public EntityModel<OrdersWrapper> getOrder(@PathVariable long customerID, @PathVariable long orderID) {
		OrdersWrapper wrapper = hateosService.getOrder(customerID, orderID);
		Link selfLink = linkTo(methodOn(HATEOSController.class).getCustomer(customerID)).withSelfRel();
		wrapper.removeLinks();
		wrapper.add(selfLink);
		return EntityModel.of(wrapper);
	}

	@GetMapping("/getCustomer/{customerID}/getAllOrders")
	public CollectionModel<OrdersWrapper> getAllOrders(@PathVariable long customerID) {
		List<OrdersWrapper> orders = hateosService.getAllOrders(customerID);
		for (final OrdersWrapper order : orders) {
			Link selfLink = linkTo(methodOn(HATEOSController.class).getOrder(customerID, order.getOrderID()))
					.withSelfRel();
			order.removeLinks();
			order.add(selfLink);
		}
		Link selfLink = linkTo(methodOn(HATEOSController.class).getAllOrders(customerID)).withSelfRel();
		return CollectionModel.of(orders, selfLink);
	}

}
