package fr.unice.polytech.si4.otake.cookiefactory.shop.exception;

/**
 * NullParentCompanyRuntimeException
 */
public class NullParentCompanyRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullParentCompanyRuntimeException() {
        super("Parent Company can not be null");
    }

}
