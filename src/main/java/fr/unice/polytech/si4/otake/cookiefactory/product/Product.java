package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Arrays;

public abstract class Product {
    protected static final String NAME_CAN_NOT_BE_NULL = "Name can not be null";
    protected final String name;
    protected final ProductType productType;
    protected double price;
    private int units;

    protected Product(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }

    protected abstract double computePrice();

    public abstract double applyTaxes(double tax);

    public abstract int getSize();

    public String getName() {
        return this.name;
    }

    public void incrementUnits(Integer value) {
        this.units += value;
    }

    public int getUnits() {
        return this.units;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public boolean isA(ProductType... types) {
        for (ProductType type : Arrays.asList(types)) {
            if (type == this.productType) {
                return true;
            }
        }
        return false;
    }

}
