package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Objects;

public class Beverage extends Product {

    public Beverage(String name, double price) {
        super(name, ProductType.BEVERAGE);
        this.price = price;
    }

    public double computePrice() {
        return this.price;
    }

    @Override
    public double applyTaxes(double tax) {
        return this.price * (1 + tax);
    }

    public int getSize() {
        return 1;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.productType);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Beverage)) {
            return false;
        }
        Beverage beverage = (Beverage) obj;
        return this.hashCode() == beverage.hashCode();
    }
}
