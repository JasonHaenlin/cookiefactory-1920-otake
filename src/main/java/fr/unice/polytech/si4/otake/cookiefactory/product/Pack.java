package fr.unice.polytech.si4.otake.cookiefactory.product;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

public class Pack extends Product {

    private PackSize size;
    private List<Product> productsInPack;

    Pack(String name, ProductType type, PackSize size){
        super(name, type);
        this.size = size;
    }

    Pack(String name, ProductType type, PackSize size, List<Product> products){
        super(name, type);
        this.size = size;
        this.productsInPack = products;
    }

    public double computePrice(){
        return 0;
    }

    public PackSize getSize(){
        return size;
    }

    public List<Product> getProductsInPack(){
        return productsInPack;
    }
}
