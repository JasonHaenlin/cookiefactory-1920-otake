package fr.unice.polytech.si4.otake.cookiefactory.order.exception;

/**
 * NotEnoughIngredientsRuntimeException
 */
public class NotEnoughIngredientsRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotEnoughIngredientsRuntimeException() {
        super("Not enough ingredients in the shop");
    }

}
