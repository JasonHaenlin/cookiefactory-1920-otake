package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import java.util.Objects;

/**
 * Ingredient
 */
public class Ingredient {

    private double price;
    private final String name;
    private final IngredientType type;

    public Ingredient(String name, double price, IngredientType type) {
        this.name = name.toLowerCase();
        this.price = price;
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the type
     */
    public IngredientType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ingredient)) {
            return false;
        }
        Ingredient i = (Ingredient) obj;
        return this.hashCode() == i.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
