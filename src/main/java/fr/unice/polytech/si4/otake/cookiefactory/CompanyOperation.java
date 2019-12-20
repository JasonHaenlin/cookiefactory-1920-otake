package fr.unice.polytech.si4.otake.cookiefactory;

import fr.unice.polytech.si4.otake.cookiefactory.discount.DiscountQueue;

/**
 * CompanyOperation
 */
public interface CompanyOperation {

    RecipeBook getRecipes();

    DiscountQueue getDiscounts();
}
