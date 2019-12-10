package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Ingredient;

public class Storage implements StorageObserver {

    private Map<Ingredient, Integer> stock;

    Storage(RecipeBook recipeBook) {
        List<Ingredient> li = recipeBook.getIngredients();
        this.stock = new HashMap<>(li.size());
        for (Ingredient i : li) {
            this.stock.put(i, 0);
        }
        recipeBook.addObserver(this);
    }

    public void addStock(Ingredient ingredient, int quantity) {
        Integer value = this.stock.get(ingredient);
        if (value == null) {
            value = 0;
        }
        this.stock.put(ingredient, value + quantity);
    }

    public void deleteStock(Ingredient ingredient, int quantity) {
        Integer value = this.stock.get(ingredient);
        if (value == null) {
            return;
        }
        this.stock.put(ingredient, value < quantity ? 0 : value - quantity);
    }

    public List<Cookie> removeListFromStockIfEnough(List<Cookie> list, Boolean remove) {
        List<Cookie> returnlist = new ArrayList<>();
        HashMap<Ingredient, Integer> copyStock = new HashMap<Ingredient, Integer>(this.stock);
        for (Cookie cookie : list) {
            if (!removeFromStockIfEnough(cookie, Boolean.TRUE)) {
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

    @Override
    public void addIngredient(Ingredient ingredient) {
        this.stock.put(ingredient, 0);
    }

    @Override
    public void removeIngredient(Ingredient ingredient) {
        this.stock.remove(ingredient);
    }

}
