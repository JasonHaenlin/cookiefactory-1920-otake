package fr.unice.polytech.si4.otake.cookiefactory;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParentCompany {
	private List<RegisteredCustomer> registeredCustomers;
	private List<Shop> shops;
	private ShopFinder shopFinder;

	public ParentCompany(){
		registeredCustomers = new ArrayList<>();
		shops = new ArrayList<>();
		shopFinder = new ShopFinder();
	}

	public void addShop(String city, String name){
		Shop shop = new Shop(city,name);
		shops.add(shop);
		shopFinder.addShop(shop);
	}

	public void addShop(Shop shop){
		shops.add(shop);
		shopFinder.addShop(shop);
	}

	public void removeShop(Shop shop){
		shops.remove(shop);
		shopFinder.removeShop(shop);
	}

	/**
	 * 
	 * @param location, name
	 */
	public List<Shop> getShopByTerms(String location, String name) {
		if(location == null && name == null){
			return new ArrayList<>();
		} else if(name == null){
			return shopFinder.getShopsByKey(location);
		} else if(location == null){
			return shopFinder.getShopsByKey(name);
		} else {
			List<Shop> matchingLocationShops = shopFinder.getShopsByKey(location);
			List<Shop> matchingNameShops = shopFinder.getShopsByKey(name);
			return matchingLocationShops.stream()
					.distinct()
					.filter(matchingNameShops::contains)
					.collect(Collectors.toList());
		}
	}

	public boolean addOrUpdateRegisteredCustomer() {
		return addOrUpdateRegisteredCustomer(false);
	}

	public boolean addOrUpdateRegisteredCustomer(boolean isSubscribed) {
		String id ="";
		id+=(registeredCustomers.size()*Math.random())+Math.random();
		return addOrUpdateRegisteredCustomer(id,isSubscribed);
	}



	public boolean addOrUpdateRegisteredCustomer(String id) {
		return addOrUpdateRegisteredCustomer(id,false);
	}

	/**
	 * @param id of the
	 * @param subscribe to fidelity program
	 */
	public boolean addOrUpdateRegisteredCustomer(String id, boolean subscribe) {
		boolean registeredSuccessfully = false;
		try {
			getRegisteredCustomer(id).setSubscribed(subscribe);
		} catch (NoSuchElementException e){
			registeredCustomers.add(new RegisteredCustomer(id,subscribe));
			registeredSuccessfully = true;
		}
		return registeredSuccessfully;
	}

	/**
	 * @param id of the registeredCustomer
	 * @throws NoSuchElementException when nothing found
	 */
	public RegisteredCustomer getRegisteredCustomer(String id) {
		for (RegisteredCustomer r: registeredCustomers ){
			if(r.getId().equals(id))
				return r;
		}
		throw new NoSuchElementException();
	}

	public List<RegisteredCustomer> getRegisteredCustomers() {
		return registeredCustomers;
	}

	public List<Shop> getShops() {
		return shops;
	}
}