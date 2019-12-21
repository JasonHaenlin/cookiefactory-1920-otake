package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

/**
 * ExtraProducts
 */
public class ExtraProducts {

    private final Set<Beverage> beverages;
    private final PackOptimizer packOptimizer;

    /**
     * extra products are packs and beverages
     */
    public ExtraProducts() {
        this.beverages = new HashSet<>();
        this.packOptimizer = new PackOptimizer();
    }

    public ExtraProducts addBeverage(Beverage beverage) {
        this.beverages.add(beverage);
        return this;
    }

    public ExtraProducts addPack(PackType pack) {
        this.packOptimizer.addPackType(pack);
        return this;
    }

    public ExtraProducts removeBeverage(Beverage beverage) {
        this.beverages.add(beverage);
        return this;
    }

    public ExtraProducts removePack(PackType pack) {
        this.packOptimizer.addPackType(pack);
        return this;
    }

    /**
     * @return the beverages
     */
    public List<Beverage> getBeverages() {
        return new ArrayList<>(this.beverages);
    }

    /**
     * @return the packOptimizer
     */
    public List<PackType> getPacks() {
        return packOptimizer.getPacks();
    }

    public List<Integer> getPackPossibleSize() {
        List<Integer> size = new ArrayList<>(this.packOptimizer.getPacks().size());
        for (PackType type : this.packOptimizer.getPacks()) {
            size.add(type.getSize());
        }
        return size;
    }

    public Map<Product, Integer> getProductOptimization(Map<Product, Integer> products) {
        return packOptimizer.optimizeProducts(products);
    }

    public Pack createPackIfPossible(Cookie cookies, int amount) {
        return createPackIfPossible(cookies, amount, null);
    }

    public Pack createPackIfPossible(Cookie cookie, int amount, Beverage beverage) {
        for (PackType type : this.packOptimizer.getPacks()) {
            if (type.getSize() == amount) {
                return new Pack(cookie.getName(), type, cookie).withBeverage(beverage);
            }
        }
        return null;
    }

}
