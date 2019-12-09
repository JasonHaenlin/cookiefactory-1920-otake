package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Objects;

public class Pack extends Product {

    private PackType packType;
    private Product product;

    Pack(String name, ProductType type, PackType packType) {
        super(name, type);
        this.packType = packType;
    }

    Pack(String name, ProductType type, PackType packType, Product product) {
        super(name, type);
        this.packType = packType;
        this.product = product;
    }

    public double computePrice() {
        return 0;
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
