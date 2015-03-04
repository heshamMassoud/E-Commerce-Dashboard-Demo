package controllers;

import io.sphere.client.shop.SphereClient;
import io.sphere.client.shop.model.Customer;
import io.sphere.client.shop.model.CustomerName;

import java.io.IOException;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result index() throws IOException {
		SphereClient sphere = SphereClient.create(sphereConfig.Config
				.load("conf/application.conf"));
		List<Customer> allCustomers = sphere.customers().all().fetch()
				.getResults();
		sphere.shutdown();
		Customer customer = allCustomers.get(0);
		CustomerName customerName = customer.getName();
		return ok(views.html.index.render("Welcome, " + customerName));
	}

}
