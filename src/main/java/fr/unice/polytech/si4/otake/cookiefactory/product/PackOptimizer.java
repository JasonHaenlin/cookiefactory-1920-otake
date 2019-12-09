package fr.unice.polytech.si4.otake.cookiefactory.product;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

import java.util.*;

public class PackOptimizer {
    private final List<PackType> packs;

    public PackOptimizer() {
        this.packs = new ArrayList<>();
    }

    public void addPackType(PackType pack) {
        this.packs.add(pack);
        Collections.sort(this.packs);
        Collections.reverse(this.packs);
    }

    public Map<Product, Integer> optimizeProducts(Map<Product, Integer> products) {
        Map<Product, Integer> optimizedProducts = new HashMap<>();
        Map<Product, Integer> productsLeft = new HashMap<>(products);
        /*List<Product> beverages = new ArrayList<>();
        for (Product p : products.keySet()) {
            if (p.getProductType() == ProductType.BEVERAGE) {
                beverages.add(p);
            }
        }*/
        for (PackType packType : this.packs){
            for(Product p : products.keySet()){
                if(!productsLeft.isEmpty() && productsLeft.get(p) >= packType.getSize()){
                    Pack newPack = new Pack("Pack", ProductType.PACK, packType, p);
                    productsLeft.put(p, (productsLeft.get(p) - packType.getSize()));
                    if(productsLeft.get(p) == 0) productsLeft.remove(p);
                    if(optimizedProducts.containsKey(newPack)){
                        optimizedProducts.put(newPack, optimizedProducts.get(newPack)+1);
                    } else {
                        optimizedProducts.putIfAbsent(newPack, 1);
                    }
                }
            }
            /*if(!beverages.isEmpty()){
                Product beverage = beverages.get(0);
                productsInPack.add(beverage);
                productsLeft.put(beverage, productsLeft.get(beverage)-1);
                if(productsLeft.get(beverage) == 0){
                    productsLeft.remove(beverage);
                }
                beverages.remove(beverage);
            }*/
        }
        optimizedProducts.putAll(productsLeft);
        return optimizedProducts;

    }
}
