package fr.unice.polytech.si4.otake.cookiefactory.product;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

public class Pack extends Product {

    private PackSize size;
    private List<Cookie> cookiesInPack;

    Pack(String name, ProductType type, PackSize size){
        super(name, type);
        this.size = size;
    }

    Pack(String name, ProductType type, PackSize size, List<Cookie> cookies){
        super(name, type);
        this.size = size;
        this.cookiesInPack = cookies;
    }

    public double computePrice(){
        return 0;
    }

    public PackSize getSize(){
        return size;
    }

    public List<Cookie> getCookiesInPack(){
        return cookiesInPack;
    }
}
