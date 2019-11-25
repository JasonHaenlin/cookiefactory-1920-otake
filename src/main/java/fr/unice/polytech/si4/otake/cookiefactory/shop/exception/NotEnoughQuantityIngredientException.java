package fr.unice.polytech.si4.otake.cookiefactory.shop.exception;

public class NotEnoughQuantityIngredientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotEnoughQuantityIngredientException() {
        super("The inventory lacks of ingredient to use");
    }
}
