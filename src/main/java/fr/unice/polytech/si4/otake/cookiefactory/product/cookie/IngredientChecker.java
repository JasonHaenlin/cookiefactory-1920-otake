package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import java.util.List;

/**
 * ListTypeChecker
 */
public class IngredientChecker {
    private final List<IngredientType> types;

    /**
     * create a new ingredient checker with the enum classes to check in the recipes
     *
     * @param types
     */
    public IngredientChecker(List<IngredientType> types) {
        this.types = types;
    }

    /**
     * check if the quantity of an ingredient in a recipe is correct
     *
     * @param <E>
     * @param type an enum inherited from Ingredient
     * @param list of ingredients to check
     * @param max  the max number of ingredients from the specific type to check
     * @return true if the recipe is wrong, false otherwise
     */
    public boolean isQuantityExcessive(IngredientType type, List<Ingredient> list, int max) {
        int quantity = 0;
        for (Ingredient i : list) {
            if (i.getType() == type) {
                quantity++;
            }
        }
        return quantity > max;
    }

    /**
     * verify a list of ingredients based on the enum classes put in the constructor
     *
     * @param enumList : ingredients to check
     * @return true if it is ok, false otherwise
     */
    public boolean verify(List<Ingredient> enumList) {
        for (IngredientType t : types) {
            if (!isEnumTypePresentOnce(t, enumList)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEnumTypePresentOnce(IngredientType type, List<Ingredient> list) {
        int c = 0;
        for (Ingredient i : list) {
            if (i.getType() == type) {
                c++;
            }
        }
        return c == 1;
    }

}
