package fr.unice.polytech.si4.otake.cookiefactory.product.cookie.exception;

public class IncorrectPackSize extends RuntimeException {

    private static final long serialVersionUID = 1L;

    IncorrectPackSize(){
        super("Number of cookies in the pack isn't matching the pack size.");
    }

}
