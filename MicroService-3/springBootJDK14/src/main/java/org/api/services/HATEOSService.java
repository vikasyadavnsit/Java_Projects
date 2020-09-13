package org.api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.api.wrapper.generic.CustomerWrapper;
import org.api.wrapper.generic.OrdersWrapper;
import org.springframework.stereotype.Service;

@Service
public class HATEOSService {

	private static List<CustomerWrapper> customers;

	@PostConstruct
	private void construct() {
		customers = new ArrayList<>();
		IntStream.range(1, 5).forEach(x -> {
			new CustomerWrapper();
			customers.add(CustomerWrapper.builder().customerID(x).age(new Date()).name("Customer-" + x)
					.orders(new ArrayList<>()).build());
		});

		customers.forEach(x -> {
			List<OrdersWrapper> orders = new ArrayList<>();
			IntStream.range(1, new Random().nextInt(5)).forEach(y -> {
				orders.add(OrdersWrapper.builder().orderID(y).cost(y * new Random().nextInt(1000))
						.quantity(new Random().nextInt(2) + 1).item("Order" + y).build());
			});
			x.setOrders(orders);
		});

	}

	public CustomerWrapper getCustomer(long customerID) {
		return customers.stream().filter(x -> x.getCustomerID() == customerID).findFirst().get();
	}

	public List<CustomerWrapper> getAllCustomers() {
		return customers;
	}

	public OrdersWrapper getOrder(long customerID, long orderID) {
		return customers.stream().filter(x -> x.getCustomerID() == customerID).findFirst().get().getOrders().stream()
				.filter(x -> x.getOrderID() == orderID).findFirst().get();
	}

	public List<OrdersWrapper> getAllOrders(long customerID) {
		return customers.stream().filter(x -> x.getCustomerID() == customerID).findFirst().get().getOrders();
	}

}
