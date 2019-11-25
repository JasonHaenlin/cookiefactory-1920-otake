package fr.unice.polytech.si4.otake.cookiefactory.order;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.ProductType;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NoAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

//TODO NEED STRONG TEST for products, don't have the time for now :'(
public class Order {

	private int id;
	private final Map<Product, Integer> orderContent;

	private SimpleDate appointmentDate;
	private double priceWithoutTaxes;
	private double priceWithTaxes;
	private Status status;
	private String code;
	private OrderObserver obs;

	/**
	 * create a new order when cookies can be add
	 */
	public Order() {
		this.orderContent = new HashMap<>();
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * add a cookie in the basked
	 *
	 * @param cookie
	 * @return the number of cookies for the given cookie added
	 */
	public int addCookie(Cookie cookie) {
		Integer t = this.orderContent.get(cookie);
		if (t == null) {
			t = 0;
		}
		this.orderContent.put(cookie, t + 1);
		buildPriceWithoutTaxes();
		return t + 1;
	}

	/**
	 * remove a cookie from the basket
	 *
	 * @param cookie
	 * @return true if the cookie has been deleted, false otherwise
	 */
	public boolean removeCookie(Cookie cookie) {
		Integer t = this.orderContent.get(cookie);
		if (t == null) {
			return false;
		}
		if (t > 1) {
			this.orderContent.put(cookie, t - 1);
		} else {
			this.orderContent.remove(cookie);
		}
		buildPriceWithoutTaxes();
		return true;
	}

	private void buildPriceWithoutTaxes() {
		priceWithoutTaxes = 0;
		for (Map.Entry<Product, Integer> entry : orderContent.entrySet()) {
			priceWithoutTaxes += entry.getKey().getPrice() * entry.getValue();
		}
	}

	private void updateCookiesSolds() {
		for (Map.Entry<Product, Integer> entry : orderContent.entrySet()) {
			ProductType type = entry.getKey().getProductType();
			if (type == ProductType.CUSTOM_COOKIE || type == ProductType.ON_MENU_COOKIE)
				entry.getKey().incrementUnits(entry.getValue());
		}

	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Update the status of the order
	 *
	 * @param status : READY, RETRIEVED, WAITING
	 */
	public void updateStatus(Status status) {
		if (status == Status.WAITING) {
			updateCookiesSolds();
		}
		this.status = status;
	}

	public boolean retrieved() {
		if (obs == null) {
			return false;
		}
		return obs.retrieved(this);
	}

	boolean hasBeenRetrieved() {
		return this.status == Status.RETRIEVED;
	}

	/**
	 * retrieve the content of the order
	 *
	 * @return a map of products with the quantities
	 */
	public Map<Product, Integer> getTheOrderContent() {
		return this.orderContent;
	}

	/**
	 * get the quantity of product in the basket
	 *
	 * @return the number of product
	 */
	public int getQuantity() {
		int quantity = 0;
		for (Map.Entry<Product, Integer> e : orderContent.entrySet()) {
			quantity += e.getValue();
		}
		return quantity;
	}

	public double getPriceWithTaxes() {
		return priceWithTaxes;
	}

	public void setPriceWithTaxes(double priceWithTaxes) {
		this.priceWithTaxes = priceWithTaxes;
	}

	public void setPriceWithoutTaxes(double priceWithoutTaxes) {
		this.priceWithoutTaxes = priceWithoutTaxes;
	}

	public double getPriceWithoutTaxes() {
		return priceWithoutTaxes;
	}

	public void setAppointmentDate(SimpleDate date) {
		this.appointmentDate = date;
	}

	public SimpleDate getAppointmentDate() {
		if (appointmentDate == null) {
			throw new NoAppointmentRuntimeException();
		}
		return appointmentDate;
	}

	public void applyDiscount(double reduction) {
		this.priceWithTaxes = (this.priceWithTaxes - (this.priceWithTaxes * reduction));
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public void setObs(OrderObserver obs) {
		this.obs = obs;
	}
}
