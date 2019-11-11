package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Ingredient;

/**
 * ListTypeChecker
 */
public class IngredientChecker {
    private final List<Class<? extends Ingredient>> listClass;

    public IngredientChecker(List<Class<? extends Ingredient>> listClass) {
        this.listClass = listClass;
    }

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
