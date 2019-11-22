package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.*;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderQueue;

public class Shop {

	private static final float DEFAULT_TAXES = (float) 0.30;

	private final String city;
	private final String name;
	private final OrderQueue orders;
	private final Scheduler schedule;
	private final CookieFactory factory;

	private Map<Integer, Float> affluencePercentagePerHour = new HashMap<>();

	private int orderCount;

	private float taxes;

	public Shop(String city, String name,CookieFactory Fact) {
		this(city, DEFAULT_TAXES, name,Fact);
	}

	public Shop(String city, float taxes, String name,CookieFactory Fact) {
		this(city, taxes, name, 8, 20,Fact);
	}

	public Shop(String city, float taxes, String name, int openingTime, int closingTime,CookieFactory Fact) {
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		this.schedule = new Scheduler(openingTime, closingTime);
		this.orders = new OrderQueue();
		this.orderCount = 0;
		this.factory = Fact;

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

	public float getTaxes() {
		return this.taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	/**
	 * apply the taxes of a shop to an order
	 *
	 * @param order
	 */
	public float applyTaxes(Order order) {
		order.setPriceWithTaxes(order.getPriceWithoutTaxes() * this.taxes + order.getPriceWithoutTaxes());
		return order.getPriceWithTaxes();
	}

	public Map<Integer, Integer> getAffluence(){
		Stack<Order> retrievedOrders = this.orders.getArchive();
		Map<Integer, Integer> affluenceMap = new HashMap<>();
		// Init map with 0 orders for each hours.
		for(int i = 0; i <= 23; i++){
			affluenceMap.putIfAbsent(i, 0);
		}

		for(Order o : retrievedOrders){
			int hour = o.getAppointmentDate().getHour();
			affluenceMap.put(hour, affluenceMap.get(hour)+1);
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
