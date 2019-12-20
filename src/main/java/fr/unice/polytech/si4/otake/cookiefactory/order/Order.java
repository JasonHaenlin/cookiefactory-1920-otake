package fr.unice.polytech.si4.otake.cookiefactory.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.BadAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.Pack;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.ProductType;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

public class Order {

	private int id;

	private final Map<Product, Integer> content;
	private final String code;
	private final SimpleDate appointmentDate;

	private double priceWithoutTaxes;
	private double priceWithTaxes;
	private boolean retrived;

	private final RegisteredCustomer rg;

	private OrderObserver obs;

	/**
	 * create a new order when cookies can be add
	 */
	Order(Map<Product, Integer> content, SimpleDate appointmentDate, String code) {
		this(content, appointmentDate, code, null);

	}

	Order(Map<Product, Integer> content, SimpleDate appointmentDate, String code, RegisteredCustomer rg) {
		this.content = content;
		this.appointmentDate = appointmentDate;
		this.code = code;
		this.retrived = false;
		this.rg = rg;
		buildPriceWithoutTaxes();
	}

	public void setId(int id) {
		this.id = id;
	}

	private final void buildPriceWithoutTaxes() {
		if (this.content == null) {
			return;
		}
		priceWithoutTaxes = 0;
		for (Map.Entry<Product, Integer> entry : content.entrySet()) {
			priceWithoutTaxes += entry.getKey().getPrice() * entry.getValue();
		}
	}

	private void updateCookiesSolds() {
		for (Map.Entry<Product, Integer> entry : content.entrySet()) {
			ProductType type = entry.getKey().getProductType();
			if (type == ProductType.CUSTOM_COOKIE || type == ProductType.ON_MENU_COOKIE)
				entry.getKey().incrementUnits(entry.getValue());
		}

	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public boolean retrieved() {
		if (obs == null) {
			return false;
		}
		this.retrived = true;
		updateCookiesSolds();
		return obs.retrieved(this);
	}

	public boolean hasBeenRetrieved() {
		return this.retrived;
	}

	/**
	 * retrieve the content of the order
	 *
	 * @return a map of products with the quantities
	 */
	public Map<Product, Integer> getContent() {
		return this.content;
	}

	/**
	 * get the quantity of product in the basket
	 *
	 * @return the number of product
	 */
	public int getQuantity() {
		int quantity = 0;
		for (Map.Entry<Product, Integer> e : content.entrySet()) {
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

	public SimpleDate getAppointmentDate() {
		if (appointmentDate == null) {
			throw new BadAppointmentRuntimeException();
		}
		return appointmentDate;
	}

	public void applyDiscount(double reduction) {
		this.priceWithTaxes = (this.priceWithTaxes - (this.priceWithTaxes * reduction));
	}

	public double applyTaxes(double taxes) {
		this.priceWithTaxes = 0;
		for (Map.Entry<Product, Integer> entry : content.entrySet()) {
			this.priceWithTaxes += (entry.getKey().applyTaxes(taxes)) * entry.getValue();
		}
		return this.priceWithTaxes;
	}

	public int getId() {
		return id;
	}

	public void setObs(OrderObserver obs) {
		this.obs = obs;
	}

	public final List<Cookie> toCookieList() {
		List<Cookie> cookies = new ArrayList<>();
		for (Map.Entry<Product, Integer> entry : content.entrySet()) {
			Product product = entry.getKey();
			int sameproduct = entry.getValue();
			if (product.isA(ProductType.CUSTOM_COOKIE, ProductType.ON_MENU_COOKIE)) {
				for (int i = 0; i < sameproduct; i++) {
					cookies.add((Cookie) product);
				}
			} else if (product.isA(ProductType.PACK)) {
				Pack pack = (Pack) product;
				for (int j = 0; j < pack.getSize(); j++) {
					cookies.add((Cookie) pack.getProductsInPack());
				}
			}
		}
		return cookies;
	}

	/**
	 * @return the rg
	 */
	public RegisteredCustomer getRegisteredCustomer() {
		return rg;
	}
}
