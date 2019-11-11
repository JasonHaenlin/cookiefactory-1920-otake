package fr.unice.polytech.si4.otake.cookiefactory.cookie.exception;

/**
 * TypeNotPresentRuntimeException
 */
public class IngredientNotPresentRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IngredientNotPresentRuntimeException() {
        super("Ingredients is missing in the recipe");
    }

}
