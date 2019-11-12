package fr.unice.polytech.si4.otake.cookiefactory.cookie.exception;

/**
 * NoToppingRuntimeException
 */
public class TooMuchIngredientRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TooMuchIngredientRuntimeException(String type, int quantity) {
        super("A cookie cannot have more than " + quantity + " " + type);
    }

}
