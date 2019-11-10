package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ParentCompany {
	private List<RegisteredCustomer> registeredCustomers;
	private List<Shop> shops;

	public ParentCompany(){
		registeredCustomers = new ArrayList<>();
		shops = new ArrayList<>();
	}

	public void addShop(String city, String name){
		shops.add(new Shop(city,name));
	}

	/**
	 * 
	 * @param terms
	 */
	public Shop[] getShopByTerms(String terms) {
		// TODO - implement ParentCompany.getShopByTerms
		throw new UnsupportedOperationException();
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