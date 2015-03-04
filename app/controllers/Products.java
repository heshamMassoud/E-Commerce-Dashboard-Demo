package controllers;

import io.sphere.client.shop.model.Product;

import java.io.IOException;
import java.util.List;

import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Products extends Controller {

	public static Result index() throws IOException {
		List<Product> allProducts = (List<Product>) Cache.get("products");
		Product p = allProducts.get(0);
		return ok(products.render("Products", allProducts.size() + " products", allProducts));
	}

}
