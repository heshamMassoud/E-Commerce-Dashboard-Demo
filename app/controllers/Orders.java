package controllers;

import io.sphere.client.shop.SphereClient;
import io.sphere.client.shop.model.Customer;
import io.sphere.client.shop.model.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import views.html.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;

public class Orders extends Controller {

	public static Result index() throws IOException {
		List<Order> allOrders = (List<Order>) Cache.get("orders");
		List<Customer> allOrdersCustomers = new ArrayList<Customer>();
		SphereClient sphere = SphereClient.create(sphereConfig.Config
				.load("conf/application.conf"));
		for (Order order : allOrders) {
			String orderCustomerId = order.getCustomerId();
			Customer orderCustomer = sphere.customers().byId(orderCustomerId)
					.fetch().get();
			allOrdersCustomers.add(orderCustomer);
		}
		return ok(orders.render("Orders", allOrders.size() + " order",
				allOrdersCustomers, allOrders));
	}
}
