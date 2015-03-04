package controllers;

import io.sphere.client.shop.model.Cart;
import io.sphere.client.shop.model.Customer;
import io.sphere.client.shop.model.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import views.html.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;

public class Customers extends Controller {

	public static Result index() throws IOException {
		List<Customer> allCustomers = (List<Customer>) Cache.get("customers");
		List<Order> allOrders = (List<Order>) Cache.get("orders");
		ArrayList<Integer> customerNumberOfOrders = new ArrayList<Integer>();
		for (int i = 0; i < allCustomers.size(); i++) {
			Customer customer = allCustomers.get(i);
			String customerId = customer.getId();
			customerNumberOfOrders.add(0);
			for (Order order : allOrders) {
				String orderCustomerId = order.getCustomerId();
				if (orderCustomerId.equals(customerId))
					customerNumberOfOrders.set(i, 1);
			}
		}
		Customer randomCustomer = allCustomers.get(0);
		return ok(customers.render("Customers", allCustomers.size()
				+ " customers", allCustomers, customerNumberOfOrders));
	}
}
