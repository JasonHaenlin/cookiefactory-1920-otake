package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Objects;

public class Pack extends Product {

    private PackType packType;
    private Product cookie;
    private Product beverage;

    public Pack(String name, PackType packType) {
        super(name, ProductType.PACK);
        this.packType = packType;
    }

    public Pack(String name, PackType packType, Product cookie) {
        super(name, ProductType.PACK);
        this.packType = packType;
        this.cookie = cookie;
    }

    /**
     *
     * @param beverage
     * @return
     */
    Pack withBeverage(Beverage beverage) {
        this.beverage = beverage;
        return this;
    }

    /**
     * compute the price, if a beverage is in the pack, we count half of the price
     *
     * @return the price
     */
    public double computePrice() {
        if (this.beverage != null) {
            return this.packType.getPrice() + (this.beverage.getPrice() / 2.);
        }
        return this.packType.getPrice();
    }

    public double applyTaxes(double tax) {
        return computePrice();
    }

    public PackType getPackType() {
        return packType;
    }

    public Product getProductsInPack() {
        return this.cookie;
    }

    @Override
    public double getPrice() {
        return computePrice();
    }

    @Override
    public int getSize() {
        return this.packType.getSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pack)) {
            return false;
        }
        Pack pack = (Pack) obj;
        return this.hashCode() == pack.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.productType, this.packType, this.cookie);
    }
}
