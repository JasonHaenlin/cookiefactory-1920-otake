package fr.unice.polytech.si4.otake.cookiefactory.shop.exception;

public class NoShopOpenedForTheProduct extends RuntimeException{
    public NoShopOpenedForTheProduct(){
        super("There is no open shop available to make the product");
    }
}
