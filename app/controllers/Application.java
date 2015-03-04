package controllers;

import io.sphere.client.model.VersionedId;
import io.sphere.client.shop.CreateOrderBuilder;
import io.sphere.client.shop.SphereClient;
import io.sphere.client.shop.model.Address;
import io.sphere.client.shop.model.Cart;
import io.sphere.client.shop.model.Cart.InventoryMode;
import io.sphere.client.shop.model.CartUpdate;
import io.sphere.client.shop.model.Customer;
import io.sphere.client.shop.model.CustomerName;
import io.sphere.client.shop.model.Order;
import io.sphere.client.shop.model.PaymentState;
import io.sphere.client.shop.model.Product;
import views.html.*;
import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

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
		
		/*
		 * Code used to create an order for a user
		 * 
		Customer customerOne = allCustomers.get(0);
		Product lastProduct = allProducts.get(allProducts.size()-1);
		Currency currency = Currency.getInstance("EUR");
		
		//sphere.carts().createCart(currency,customerOneId, Cart.InventoryMode.valueOf("ReserverOnOrder"));
		
		
		//Cart cart = sphere.carts().createCart(currency,customerOne.getId(), InventoryMode.None).execute();
		Cart cart = sphere.carts().forCustomer(customerOne.getId()).fetch().get();
		
		CartUpdate updateCart = new CartUpdate();
		updateCart.addLineItem(2, lastProduct.getId());
		
		updateCart.setShippingAddress(customerOne.getAddresses().get(0));
		cart = sphere.carts().updateCart(cart.getIdAndVersion(), updateCart).execute();
		
		CreateOrderBuilder orderBuilder = new CreateOrderBuilder(cart.getIdAndVersion(), PaymentState.Paid);
		Order order = sphere.orders().createOrder(orderBuilder).execute();
		
		String totalPriceInCart = cart.getTotalPrice().toString();
		*/
		

		sphere.shutdown();
		return ok(index.render("Dashboard", "Demo" + "-" + allOrders.size()));// + order.getOrderNumber() + "-" + order.getTotalQuantity() + "-"));
	}

}
