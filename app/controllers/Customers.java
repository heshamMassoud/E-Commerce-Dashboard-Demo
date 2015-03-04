package controllers;

import io.sphere.client.shop.model.Customer;

import java.io.IOException;
import java.util.List;
import views.html.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;

public class Customers extends Controller {

	public static Result index() throws IOException {
		List<Customer> allCustomers = (List<Customer>) Cache.get("customers");
		return ok(customers.render("Customers", allCustomers.size() + " customers", allCustomers));
	}

}
