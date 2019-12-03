package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactoryAPI;
import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.discount.DiscountQueue;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderQueue;
import fr.unice.polytech.si4.otake.cookiefactory.shop.exception.NullParentCompanyRuntimeException;

public class Shop {

	private static final double DEFAULT_TAXES = 0.30;

	private final String city;
	private final String name;
	private final OrderQueue orders;
	private final Scheduler schedule;
	private final ParentCompany parentCompany;
	private final Storage inventory;
	private final List<String> Cinemas;

	private int orderCount;

	private double taxes;

	// TODO update to real inventory when ready OR use mock for all test ¯\_(ツ)_/¯
	public Shop(String city, String name, ParentCompany parentCompany) {
		this.city = city;
		this.taxes = DEFAULT_TAXES;
		this.name = name;
		this.parentCompany = parentCompany;
		this.schedule = new Scheduler(8, 20);
		this.orders = new OrderQueue();
		this.inventory = new Storage();
		this.orderCount = 0;
		this.Cinemas = new ArrayList<>();
	}

	public Shop withSchedule(int opening, int closing) {
		this.schedule.setSchedule(opening, closing);
		return this;
	}

	public Shop withCustomTaxes(double taxes) {
		this.taxes = taxes;
		return this;
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
		if (addOrder(order)) {
			registerCustomer.addPoints(order.getQuantity());
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
		return date.getHour() > schedule.getOpeningHour() && date.getHour() <= schedule.getClosingHour();
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

	public void addCinema(String cinema) {
		this.Cinemas.add(cinema);
	}

	public void delCinema(String cinema) {
		for (int i = 0; i < this.Cinemas.size(); i++) {
			if (this.Cinemas.get(i).equals(cinema)) {
				this.Cinemas.remove(i);
			}
		}
	}

	private boolean checkCinema(String cinemaName) {
		for (String string : Cinemas) {
			if (string.equals(cinemaName)) {
				return true;
			}
		}
		return false;
	}

	// the ticket need to be "name:ticketid" like : "pathepourchat:EBSU18E"
	public Boolean checkTicket(String ticket) {
		String[] arrOfStr = ticket.split(":", 2);
		CookieFactoryAPI cookiefactoryapi = CookieFactoryAPI.getInstanceCookieFactoryAPI();
		if (checkCinema(arrOfStr[0]) && cookiefactoryapi.globalyCheck(arrOfStr[1])) {
			return true;
		}
		return false;
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

	public DiscountQueue getDiscounts() {
		if (parentCompany == null) {
			throw new NullParentCompanyRuntimeException();
		}
		return this.parentCompany.getDiscounts();
	}

	public Storage getStorage() {
		return inventory;
	}

	public boolean isCookieAvailable(String cookieName) {
		RecipeBook recipeBook = parentCompany.getRecipeBook();
		if (recipeBook.getCookie(cookieName) == null){
			return false;
		}

		return inventory.removeFromStockIfEnough(recipeBook.getCookie(cookieName), false);
	}
}
