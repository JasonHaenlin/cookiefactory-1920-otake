package fr.unice.polytech.si4.otake.cookiefactory;

public class Cookie {

	private float price;
	private String name;

	public Cookie(String name , float price){
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public float getPrice() {
		return this.price;
	}

}