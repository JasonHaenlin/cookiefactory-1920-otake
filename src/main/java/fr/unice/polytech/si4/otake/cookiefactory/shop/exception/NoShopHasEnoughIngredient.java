package fr.unice.polytech.si4.otake.cookiefactory.shop.exception;

public class NoShopHasEnoughIngredient extends RuntimeException {
    public NoShopHasEnoughIngredient(){
        super("Among all shop of the company, there is not any which has enough ingredient to make the product");
    }
}
