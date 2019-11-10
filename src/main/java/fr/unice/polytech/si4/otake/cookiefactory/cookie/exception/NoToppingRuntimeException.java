package fr.unice.polytech.si4.otake.cookiefactory.cookie.exception;

/**
 * NoToppingRuntimeException
 */
public class NoToppingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoToppingRuntimeException() {
        super("A cookie need at least one topping");
    }

}
