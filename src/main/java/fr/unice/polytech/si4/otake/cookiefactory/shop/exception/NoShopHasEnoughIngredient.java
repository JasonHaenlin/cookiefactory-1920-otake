package fr.unice.polytech.si4.otake.cookiefactory.shop.exception;

public class NoShopHasEnoughIngredient extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoShopHasEnoughIngredient() {
        super("Among all shop of the company, there is not any which has enough ingredient to make the product");
    }
}
