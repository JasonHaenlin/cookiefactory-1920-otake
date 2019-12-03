package fr.unice.polytech.si4.otake.cookiefactory.product;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackOptimizer {

    private Map<PackSize, Double> packPrices = new HashMap<>();
    private Map<PackSize, Integer> packSizes = new HashMap<>();

    public PackOptimizer(){}

    public void addPackType(PackSize packSize, Integer size, Double price){
        packPrices.put(packSize, price);
        packSizes.put(packSize, size);
    }

    public List<Product> optimizeProducts(List<Product> products){
        List<Product> optimizedProducts = new ArrayList<>();
        int nbCookies = 0;
        List<Product> cookiesToCheck = new ArrayList<>();
        List<Product> otherProducts = new ArrayList<>();
        for(Product p : products){
            if(p.getProductType() == ProductType.CUSTOM_COOKIE || p.getProductType() == ProductType.ON_MENU_COOKIE){
                nbCookies++;
                cookiesToCheck.add(p);
            } else {
                otherProducts.add(p);
            }
        }

        for(PackSize packSize : packSizes.keySet()){
            int size = packSizes.get(packSize);
            int nbPackForSize = nbCookies/size;
            for(int i = 0; i < nbPackForSize; ++i){
                List<Product> cookiesInPack = new ArrayList<>();
                if(nbCookies > size){
                    for(int k = 0; k < size; ++k){
                        cookiesInPack.add(cookiesToCheck.get(k));
                    }
                    cookiesToCheck.subList(0, size).clear();

                    Pack pack = new Pack("Pack", ProductType.PACK, packSize, cookiesInPack);
                    optimizedProducts.add(pack);
                }
                nbCookies -= size;
            }
        }
        optimizedProducts.addAll(cookiesToCheck);
        optimizedProducts.addAll(otherProducts);
        return optimizedProducts;
    }
}
