package fr.unice.polytech.si4.otake.cookiefactory.shop;

class NotEnoughQuantityIngredientException extends RuntimeException {

    NotEnoughQuantityIngredientException(){
        super("The inventory lacks of ingredient to use");
    }
}
