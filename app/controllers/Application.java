package controllers;

import io.sphere.client.shop.SphereClient;
import io.sphere.client.shop.model.Customer;
import io.sphere.client.shop.model.CustomerName;
import io.sphere.client.shop.model.Order;
import io.sphere.client.shop.model.Product;
import views.html.*;
import java.io.IOException;
import java.util.List;

import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index() throws IOException {
		SphereClient sphere = SphereClient.create(sphereConfig.Config
				.load("conf/application.conf"));
		List<Customer> allCustomers = sphere.customers().all().fetch()
				.getResults();
		Cache.set("customers", allCustomers);

		List<Product> allProducts = sphere.products().all().fetch()
				.getResults();
		Cache.set("products", allProducts);

		List<Order> allOrders = sphere.orders().all().fetch().getResults();
		Cache.set("orders", allOrders);

		sphere.shutdown();
		return ok(index.render("Dashboard", "Demo"));
	}

}
