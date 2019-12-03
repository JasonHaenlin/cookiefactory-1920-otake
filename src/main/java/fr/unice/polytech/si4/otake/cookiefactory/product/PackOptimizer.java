package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Product> optimizeProducts(List<Product> products) {
        List<Product> optimizedProducts = new ArrayList<>();
        int nbCookies = 0;
        List<Product> cookiesToCheck = new ArrayList<>();
        List<Product> otherProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getProductType() == ProductType.CUSTOM_COOKIE || p.getProductType() == ProductType.ON_MENU_COOKIE) {
                nbCookies++;
                cookiesToCheck.add(p);
            } else {
                otherProducts.add(p);
            }
        }
        for (PackType pack : this.packs) {
            int packSize = pack.getSize();
            int nbPackForSize = nbCookies / packSize;
            for (int i = 0; i < nbPackForSize; ++i) {
                List<Product> cookiesInPack = new ArrayList<>();
                if (nbCookies > packSize) {
                    for (int k = 0; k < packSize; ++k) {
                        cookiesInPack.add(cookiesToCheck.get(k));
                    }
                    cookiesToCheck.subList(0, packSize).clear();

                    Pack newPack = new Pack("Pack", ProductType.PACK, pack, cookiesInPack);
                    optimizedProducts.add(newPack);
                    nbCookies -= packSize;
                }
            }
        }
        optimizedProducts.addAll(cookiesToCheck);
        optimizedProducts.addAll(otherProducts);
        return optimizedProducts;
    }
}
