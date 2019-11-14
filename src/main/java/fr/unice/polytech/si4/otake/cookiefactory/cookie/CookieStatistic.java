package fr.unice.polytech.si4.otake.cookiefactory.cookie;

public class CookieStatistic {

	private int unitsSold;
	private float percentageSold;

	public CookieStatistic() {
		this(0, 0);
	}

	public CookieStatistic(int unit, float percentage) {
		this.percentageSold = percentage;
		this.unitsSold = unit;
	}

	public void incrementUnit(int unit) {
		this.unitsSold = unitsSold + unit;
	}

	public void updatePercentage(int perc) {
		percentageSold = (percentageSold / perc) * 100;
	}

	public float getPercentageSold() {
		return this.percentageSold;
	}

}
