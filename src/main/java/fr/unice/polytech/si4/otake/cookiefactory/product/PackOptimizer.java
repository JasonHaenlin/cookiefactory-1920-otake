package fr.unice.polytech.si4.otake.cookiefactory.product;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

import java.util.*;

public class PackOptimizer {
    private EnumMap<PackSize, Integer> packSizes = new EnumMap<>(PackSize.class);
    private Map<Integer, PackSize> sizeToPack = new HashMap<>();

    public PackOptimizer(){}

    public void addPackType(PackSize packSize, Integer size){
        packSizes.put(packSize, size);
        sizeToPack.put(size, packSize);
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
        List<Integer> sizes = new ArrayList<>(packSizes.values());
        Collections.sort(sizes);
        Collections.reverse(sizes);
        for(Integer packSize : sizes){
            int nbPackForSize = nbCookies/packSize;
            System.out.println("PackSize = " + packSize + " Nb Pack : " + nbPackForSize);
            for(int i = 0; i < nbPackForSize; ++i){
                List<Product> cookiesInPack = new ArrayList<>();
                if(nbCookies > packSize){
                    for(int k = 0; k < packSize; ++k){
                        cookiesInPack.add(cookiesToCheck.get(k));
                    }
                    cookiesToCheck.subList(0, packSize).clear();

                    Pack pack = new Pack("Pack", ProductType.PACK, sizeToPack.get(packSize), cookiesInPack);
                    optimizedProducts.add(pack);
                    nbCookies -= packSize;
                }
            }
        }
        optimizedProducts.addAll(cookiesToCheck);
        optimizedProducts.addAll(otherProducts);
        return optimizedProducts;
    }
}
