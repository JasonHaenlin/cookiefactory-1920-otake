package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;

/**
 * ListTypeChecker
 */
public class IngredientChecker {
    private final List<Class<? extends Ingredient>> listClass;

    /**
     * create a new ingredient checker with the enum classes to check in the recipes
     *
     * @param listClass
     */
    public IngredientChecker(List<Class<? extends Ingredient>> listClass) {
        this.listClass = listClass;
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
    @SuppressWarnings("unchecked")
    public <E extends Enum<E>> boolean isQuantityAbused(Class<? extends Ingredient> type, List<Ingredient> list,
            int max) {
        int quantity = 0;
        for (Ingredient i : list) {
            if (EnumUtils.isValidEnum((Class<E>) type, i.toString())) {
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
        for (Class<? extends Ingredient> c : listClass) {
            if (!isEnumTypePresent(c, enumList)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <E extends Enum<E>> boolean isEnumTypePresent(Class<? extends Ingredient> type, List<Ingredient> list) {
        for (Ingredient i : list) {
            if (EnumUtils.isValidEnum((Class<E>) type, i.toString())) {
                return true;
            }
        }
        return false;
    }

}
