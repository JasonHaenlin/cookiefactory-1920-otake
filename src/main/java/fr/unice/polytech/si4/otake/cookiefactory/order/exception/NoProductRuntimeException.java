package fr.unice.polytech.si4.otake.cookiefactory.order.exception;

/**
 * NoProductRuntimeException
 */
public class NoProductRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoProductRuntimeException() {
        super("Cannot make an order without products");
    }

}
