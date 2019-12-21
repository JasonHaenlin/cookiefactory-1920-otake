package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackOptimizer {
    private final List<PackType> packs;

    public PackOptimizer() {
        this.packs = new ArrayList<>();
    }

    public List<PackType> getPacks() {
        return packs;
    }

    public void addPackType(PackType pack) {
        this.packs.add(pack);
        Collections.sort(this.packs);
        Collections.reverse(this.packs);
    }

    /**
     * try to optimize the products to make pack if the amount of basic cookie is
     * enough. Can contain beverages too if present.
     *
     * @param products
     * @return
     */
    public Map<Product, Integer> optimizeProducts(Map<Product, Integer> products) {
        if (products.isEmpty()) {
            return products;
        }

        Map<Product, Integer> optimizedProducts = new HashMap<>();
        Map<Product, Integer> productsLeft = new HashMap<>(products);
        Map<Product, Integer> beverages = new HashMap<>();

        for (Map.Entry<Product, Integer> p : products.entrySet()) {
            if (p.getKey().isA(ProductType.BEVERAGE)) {
                beverages.put(p.getKey(), p.getValue());
            }
        }

        for (Product p : products.keySet()) {
            if (!p.isA(ProductType.BEVERAGE)) {
                PackType type = retrieveBestPackType(p, productsLeft.get(p));
                while (type != null) {
                    Pack newPack = new Pack(p.getName(), type, p)
                            .withBeverage(extractBestBeverage(beverages, productsLeft));
                    productsLeft.put(p, (productsLeft.get(p) - type.getSize()));
                    productsLeft.remove(p, 0);
                    Integer value = optimizedProducts.get(newPack);
                    optimizedProducts.put(newPack, 1 + (value == null ? 0 : value));
                    type = retrieveBestPackType(p, productsLeft.get(p));
                }
            }

        }
        optimizedProducts.putAll(productsLeft);
        return optimizedProducts;

    }

    private Product extractBestBeverage(Map<Product, Integer> beverages, Map<Product, Integer> productsLeft) {
        if (beverages.isEmpty()) {
            return null;
        }

        Product beverage = null;
        for (Product b : beverages.keySet()) {
            if (beverage == null) {
                beverage = b;
            }
            if (beverage.getPrice() < b.getPrice()) {
                beverage = b;
            }
        }
        beverages.put(beverage, beverages.get(beverage) - 1);
        beverages.remove(beverage, 0);
        productsLeft.put(beverage, productsLeft.get(beverage) - 1);
        productsLeft.remove(beverage, 0);
        return beverage;

    }

    private PackType retrieveBestPackType(Product p, Integer amount) {
        if (p == null || amount == null) {
            return null;
        }
        if (!p.isA(ProductType.ON_MENU_COOKIE)) {
            return null;
        }
        for (PackType packType : packs) {
            if (amount >= packType.getSize()) {
                return packType;
            }
        }
        return null;
    }
}
