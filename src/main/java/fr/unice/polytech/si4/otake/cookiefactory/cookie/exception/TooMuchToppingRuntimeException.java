package fr.unice.polytech.si4.otake.cookiefactory.cookie.exception;

/**
 * NoToppingRuntimeException
 */
public class TooMuchToppingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TooMuchToppingRuntimeException(int quantity) {
        super("A cookie cannot have more than " + quantity);
    }

}
