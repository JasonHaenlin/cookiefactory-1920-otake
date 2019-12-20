package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Objects;

public class Pack extends Product {

    private PackType packType;
    private Product product;

    public Pack(String name, PackType packType) {
        super(name, ProductType.PACK);
        this.packType = packType;
    }

    public Pack(String name, PackType packType, Product product) {
        super(name, ProductType.PACK);
        this.packType = packType;
        this.product = product;
    }

    public double computePrice() {
        return this.packType.getPrice();
    }

    public double applyTaxes(double tax){
        return computePrice();
    }

    public Product getProductsInPack() {
        return this.product;
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
        return Objects.hash(this.name, this.productType, this.packType, this.product);
    }
}
