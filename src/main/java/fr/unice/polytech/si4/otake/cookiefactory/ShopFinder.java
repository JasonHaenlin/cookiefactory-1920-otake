package fr.unice.polytech.si4.otake.cookiefactory;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShopFinder {

    private HashMap<String, Set<Shop>> shopCache;

    ShopFinder() {
        shopCache = new HashMap<>();
    }

    void addShop(Shop shop) {
        addKeyToMap(shop.getLocation(), shop);
        addKeyToMap(shop.getName(), shop);
    }

    private void addKeyToMap(String key, Shop shop) {
        key = normalizeKey(key);
        if (!shopCache.containsKey(key)) {
            shopCache.put(key, new HashSet<>());
        }
        shopCache.get(key).add(shop);
    }

    void removeShop(Shop shop) {
        removeShopFromKey(shop, shop.getLocation());
        removeShopFromKey(shop, shop.getName());
    }

    private void removeShopFromKey(Shop shop, String key) {
        key = normalizeKey(key);
        Set<Shop> matchingKeyShop = new HashSet<>(getShopsByKey(key));
        matchingKeyShop.remove(shop);
        shopCache.put(key, matchingKeyShop);
    }

    List<Shop> getShopsByKey(String key) {
        if (key == null)
            return new ArrayList<>();
        key = normalizeKey(key);
        return new ArrayList<>(shopCache.get(key));
    }

    private String normalizeKey(String key) {
        return Normalizer.normalize(key, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
}
