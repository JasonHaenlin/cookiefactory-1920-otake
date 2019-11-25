package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

public class CookieStatistic {

	private int unitsSold;
	private double percentageSold;

	public CookieStatistic() {
		this(0, 0);
	}

	public CookieStatistic(int unit, double percentage) {
		this.percentageSold = percentage;
		this.unitsSold = unit;
	}

	public void incrementUnit(int unit) {
		this.unitsSold = unitsSold + unit;
	}

	public void updatePercentage(int perc) {
		percentageSold = (percentageSold / perc) * 100;
	}

	public double getPercentageSold() {
		return this.percentageSold;
	}

}
