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
        /*Map<Product, Integer> productsLeft = new HashMap<>(products);
        List<Product> beverages = new ArrayList<>();
        for (Product p : products.keySet()) {
            if (p.getProductType() == ProductType.BEVERAGE) {
                beverages.add(p);
            }
        }
        for (PackType pack : this.packs){
            List<Product> productsInPack = new ArrayList<>();
            for(Product p : productsLeft.keySet()){
                int nbProduct = productsLeft.get(p);
                if(nbProduct >= pack.getSize()){
                    for(int i = 0; i < nbProduct; ++i){
                        productsInPack.add(p);
                    }
                    cookiesToCheck.subList(0, packSize).clear();
                    // TODO need to change the list
                    Pack newPack = new Pack("Pack", ProductType.PACK, pack, cookiesInPack.get(0));
                    optimizedProducts.add(newPack);
                    nbCookies -= packSize;
                }
            }
            if(!beverages.isEmpty()){
                Product beverage = beverages.get(0);
                productsInPack.add(beverage);
                productsLeft.put(beverage, productsLeft.get(beverage)-1);
                if(productsLeft.get(beverage) == 0){
                    productsLeft.remove(beverage);
                }
                beverages.remove(beverage);
            }
            Pack newPack = new Pack("Pack", ProductType.PACK, pack, productsInPack);
            optimizedProducts.put(newPack, optimizedProducts.getOrDefault(newPack, 0)+1);
        }

        optimizedProducts.putAll(productsLeft);
        for(Product p : beverages){
            optimizedProducts.put(p, optimizedProducts.getOrDefault(p, 0)+1);
        }*/
        return optimizedProducts;

    }
}
