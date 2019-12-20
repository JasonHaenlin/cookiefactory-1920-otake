package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import fr.unice.polytech.si4.otake.cookiefactory.discount.Discount;
import fr.unice.polytech.si4.otake.cookiefactory.discount.DiscountQueue;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.ShopFinder;

public class ParentCompany implements CompanyOperation {
	private final Set<RegisteredCustomer> registeredCustomers;
	private final Set<Shop> shops;
	private final ShopFinder shopFinder;
	private final RecipeBook recipeBook;
	private final DiscountQueue discounts;

	/**
	 * parentCompany where we can manage the registeredCustomer and the shops
	 */
	public ParentCompany() {
		this.registeredCustomers = new HashSet<>();
		this.shops = new HashSet<>();
		this.shopFinder = new ShopFinder();
		this.recipeBook = new RecipeBook();
		this.discounts = new DiscountQueue();
	}

	public ParentCompany withDefaultDiscount() {
		this.discounts.add(new Discount(true, 0.1, Discount.Trigger.code("EVENT"), Discount.Behaviour.products(100)));
		this.discounts.add(new Discount(true, 0.05,
				Discount.Trigger.codeStartWith("CE", Arrays.asList("POLYTECH", "OTAKE", "AREE AT PHIMAI")),
				Discount.Behaviour.basic()));
		this.discounts.add(new Discount(false, 0.01, Discount.Trigger.seniority(), Discount.Behaviour.enrolmentTime()));
		this.discounts.add(
				new Discount(false, 0.3, Discount.Trigger.hour(), Discount.Behaviour.elligibleCookies(recipeBook)));
		this.discounts
				.add(new Discount(false, 0.1, Discount.Trigger.fidelity(30), Discount.Behaviour.customerPoints(30)));
		return this;
	}

	/**
	 * add a new shop with a city and a name
	 *
	 * @param city
	 * @param name
	 */
	public void addShop(String city, String name) {
		Shop shop = new Shop(city, name, this);
		shops.add(shop);
		shopFinder.addShop(shop);
	}

	public void addShop(Shop shop) {
		shops.add(shop);
		shopFinder.addShop(shop);
	}

	public void removeShop(Shop shop) {
		shops.remove(shop);
		shopFinder.removeShop(shop);
	}

	/**
	 *
	 * @param location, name
	 */
	public List<Shop> getShopByTerms(String location, String name) {
		if (location == null && name == null) {
			return new ArrayList<>();
		} else if (name == null || "".equals(name)) {
			return shopFinder.getShopsByKey(location);
		} else if (location == null || "".equals(location)) {
			return shopFinder.getShopsByKey(name);
		} else {
			List<Shop> matchingLocationShops = shopFinder.getShopsByKey(location);
			List<Shop> matchingNameShops = shopFinder.getShopsByKey(name);
			return matchingLocationShops.stream().distinct().filter(matchingNameShops::contains)
					.collect(Collectors.toList());
		}
	}

	public boolean addOrUpdateRegisteredCustomer() {
		return addOrUpdateRegisteredCustomer(false);
	}

	public boolean addOrUpdateRegisteredCustomer(boolean isSubscribed) {
		String id = "";
		id += (registeredCustomers.size() * Math.random()) + Math.random();
		return addOrUpdateRegisteredCustomer(id, isSubscribed);
	}

	public boolean addOrUpdateRegisteredCustomer(String id) {
		return addOrUpdateRegisteredCustomer(id, false);
	}

	/**
	 * @param id        of the
	 * @param subscribe to fidelity program
	 */
	public boolean addOrUpdateRegisteredCustomer(String id, boolean subscribe) {
		boolean registeredSuccessfully = false;
		try {
			getRegisteredCustomer(id).setSubscribed(subscribe);
		} catch (NoSuchElementException e) {
			registeredCustomers.add(new RegisteredCustomer(id, subscribe));
			registeredSuccessfully = true;
		}
		return registeredSuccessfully;
	}

	/**
	 * @param id of the registeredCustomer
	 * @throws NoSuchElementException when nothing found
	 */
	public RegisteredCustomer getRegisteredCustomer(String id) {
		for (RegisteredCustomer r : registeredCustomers) {
			if (r.getId().equals(id))
				return r;
		}
		throw new NoSuchElementException();
	}

	public List<RegisteredCustomer> getRegisteredCustomers() {
		return new ArrayList<>(this.registeredCustomers);
	}

	public List<Shop> getShops() {
		return new ArrayList<>(this.shops);
	}

	@Override
	public DiscountQueue getDiscounts() {
		return discounts;
	}

	@Override
	public RecipeBook getRecipes() {
		return recipeBook;
	}
}
