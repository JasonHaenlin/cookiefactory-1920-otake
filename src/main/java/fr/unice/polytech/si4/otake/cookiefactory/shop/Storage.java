package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Topping;

public class Storage {

    Map<Ingredient, Integer> stock;

    public Storage() {
        HashMap<Ingredient, Integer> initStock = new HashMap<Ingredient, Integer>();
        initStock.put(Cooking.CHEWY, 0);
        initStock.put(Cooking.CRUNCHY, 0);
        initStock.put(Dough.CHOCOLATE, 0);
        initStock.put(Dough.OATMEAL, 0);
        initStock.put(Dough.PEANUTBUTTER, 0);
        initStock.put(Dough.PLAIN, 0);
        initStock.put(Flavour.CHILI, 0);
        initStock.put(Flavour.CINNAMON, 0);
        initStock.put(Flavour.VANILLA, 0);
        initStock.put(Mix.MIXED, 0);
        initStock.put(Mix.TOPPED, 0);
        initStock.put(Topping.MILKCHOCOLATE, 0);
        initStock.put(Topping.MMS, 0);
        initStock.put(Topping.REESEBUTTERCUP, 0);
        initStock.put(Topping.WHITECHOCOLATE, 0);
        this.stock = initStock;
    }

    public Storage(HashMap<Ingredient, Integer> maps) {
        this();
        for (Map.Entry<Ingredient, Integer> entry : maps.entrySet()) {
            Ingredient ingredient = entry.getKey();
            this.stock.put(ingredient, entry.getValue());
        }
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        if (this.stock.containsKey(ingredient)) {
            int old = this.stock.get(ingredient);
            this.stock.put(ingredient, old + quantity);
        } else {
            this.stock.put(ingredient, quantity);
        }
    }

    public void deleteIngredient(Ingredient ingredient, int quantity) {
        if (this.stock.containsKey(ingredient)) {
            int old = this.stock.get(ingredient);
            if (old < quantity) {
                this.stock.put(ingredient, 0);
            } else {
                this.stock.put(ingredient, old - quantity);
            }
        }
    }

    public List<Cookie> removeListFromStockIfEnough(List<Cookie> list , Boolean remove) {
        List<Cookie> returnlist = new ArrayList<>();
        HashMap<Ingredient, Integer> copyStock = new HashMap<Ingredient, Integer>(this.stock);
        for (Cookie cookie : list) {
            if (!removeFromStockIfEnough(cookie, true)) {
                returnlist.add(cookie);
            }
        }
        if (returnlist.size() == 0 && !remove) {
            this.stock = copyStock;
        } else if (returnlist.size() != 0) {
            this.stock = copyStock;
        }
        
        return returnlist;
    }

    public boolean removeFromStockIfEnough(Cookie cookie, Boolean updateStock) {
        List<Ingredient> list = cookie.getIngredients();
        for (Ingredient iterable_element : list) {
            int old = stock.get(iterable_element);
            if (old <= 0) {
                return false;
            }
        }
        if (updateStock) {
            updateStock(cookie);
        }

        return true;
    }

    private void updateStock(Cookie cookie) {
        List<Ingredient> list = cookie.getIngredients();
        for (Ingredient iterable_element : list) {
            int old = stock.get(iterable_element);
            stock.put(iterable_element, old - 1);
        }
    }

}
