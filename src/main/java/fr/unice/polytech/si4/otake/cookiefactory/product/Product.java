package fr.unice.polytech.si4.otake.cookiefactory.product;

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

    public String getName(){
        return this.name;
    }

    public void incrementUnits(Integer value){
        this.units+=value;
    }

    public int getUnits(){
        return this.units;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }
}
