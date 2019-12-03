package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.List;

public class Pack extends Product {

    private PackType size;
    private List<Product> productsInPack;

    Pack(String name, ProductType type, PackType size) {
        super(name, type);
        this.size = size;
    }

    Pack(String name, ProductType type, PackType size, List<Product> products) {
        super(name, type);
        this.size = size;
        this.productsInPack = products;
    }

    public double computePrice() {
        return 0;
    }

    public PackType getSize() {
        return size;
    }

    public List<Product> getProductsInPack() {
        return productsInPack;
    }

    @Override
    public int retrieveSize() {
        return this.size.getSize();
    }
}
