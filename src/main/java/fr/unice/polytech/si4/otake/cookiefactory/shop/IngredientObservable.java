package fr.unice.polytech.si4.otake.cookiefactory.shop;

/**
 * IngredientInterface
 */
public interface IngredientObservable {

    void addObserver(StorageObserver obs);

    void removeObserver(StorageObserver obs);

    void askForUpdates(StorageObserver obs);
}
