package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.*;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderQueue;

public class Shop {

	private static final double DEFAULT_TAXES = 0.30;

	private final String city;
	private final String name;
	private final OrderQueue orders;
	private final Scheduler schedule;
	private final RecipeBook recipeBook;
	private final ShopInventory inventory;

	private int orderCount;

	private double taxes;

	public Shop(String city, String name, RecipeBook recipeBook) {
		this(city, DEFAULT_TAXES, name, recipeBook);
	}

	public Shop(String city, double taxes, String name, RecipeBook recipeBook) {
		this(city, taxes, name, 8, 20, recipeBook);
	}

	public Shop(String city, double taxes, String name, int openingTime, int closingTime, RecipeBook recipeBook) {
		this(city, taxes, name, openingTime, closingTime, recipeBook, new ShopInventory());
	}

	public Shop(String city, double taxes, String name, int openingTime, int closingTime, RecipeBook recipeBook,
			ShopInventory inventory) {
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		this.schedule = new Scheduler(openingTime, closingTime);
		this.orders = new OrderQueue();
		this.orderCount = 0;
		this.recipeBook = recipeBook;
		this.inventory = inventory;
		// TODO update to real inventory when ready OR use mock for all test ¯\_(ツ)_/¯
	}

	public boolean addOrder(Order order) {
		if (checkAppointmentDate(order.getAppointmentDate())) {
			order.setId(this.orderCount);
			this.orderCount++;
			return orders.add(order);
		}
		return false;
	}

	/**
	 *
	 * @param order
	 * @param registerCustomer
	 */
	public boolean addOrder(Order order, RegisteredCustomer registerCustomer) {
		order.setPriceWithoutTaxes(registerCustomer.addDiscountIfEligible(order.getPriceWithoutTaxes()));
		if (addOrder(order)) {
			registerCustomer.addCookiePoints(order.getQuantity());
			return true;
		}
		return false;
	}

	/**
	 * @return the schedule
	 */
	public Scheduler getSchedule() {
		return schedule;
	}

	/**
	 *
	 * @param date
	 */
	public boolean checkAppointmentDate(SimpleDate date) {
		return date.getHour() > schedule.getOpeningHour() && date.getHour() < schedule.getClosingHour();
	}

	public double getTaxes() {
		return this.taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public Map<Integer, Integer> getAffluence() {
		Queue<Order> retrievedOrders = this.orders.getArchive();
		Map<Integer, Integer> affluenceMap = new HashMap<>(24);
		// Init map with 0 orders for each hours.
		for (int i = 0; i <= 23; i++) {
			affluenceMap.putIfAbsent(i, 0);
		}

		for (Order o : retrievedOrders) {
			int hour = o.getAppointmentDate().getHour();
			affluenceMap.put(hour, affluenceMap.get(hour) + 1);
		}

		return affluenceMap;
	}

	public String getLocation() {
		return city;
	}

	public Order getOrderToRetrieve(int id) {
		for (Order o : orders.toRetrieve()) {
			if (o.getId() == id) {
				return o;
			}
		}
		return null;
	}

	public boolean retrieved(int id) {
		Order o = getOrderToRetrieve(id);
		if (o == null) {
			return false;
		}
		return o.retrieved();
	}

	public Order getCurrentOrder() {
		return this.orders.peek();
	}

	public Order getNextOrder() {
		return this.orders.next();
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Shop)) {
			return false;
		}
		Shop shop = (Shop) obj;
		return this.hashCode() == shop.hashCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.city, this.name);
	}

}
