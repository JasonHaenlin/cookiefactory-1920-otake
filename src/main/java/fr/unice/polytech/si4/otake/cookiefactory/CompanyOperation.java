package fr.unice.polytech.si4.otake.cookiefactory;

import fr.unice.polytech.si4.otake.cookiefactory.discount.DiscountQueue;
import fr.unice.polytech.si4.otake.cookiefactory.product.ExtraProducts;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

/**
 * CompanyOperation
 */
public interface CompanyOperation {

    RecipeBook getRecipes();

    DiscountQueue getDiscounts();

    Cookie getRecipeOfTheDay();

    ExtraProducts getExtra();
}
