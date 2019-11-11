package fr.unice.polytech.si4.otake.cookiefactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class parentCompanyTest {

    private ParentCompany parentCompany;

    @Before
    public void parentCompanyCreation(){
        parentCompany = new ParentCompany();
    }

    @Test
    public void getShopsByTermTest(){
        Shop shop1 = new Shop("Antibes", "Les bons cookies");
        Shop shop2 = new Shop("Antibes", "Cookie Miam");
        Shop shop3 = new Shop("Avignon", "J'aime les cookies");
        Shop shop4 = new Shop("Avignon", "Cookie Miam");
        parentCompany.addShop(shop1);
        parentCompany.addShop(shop2);
        parentCompany.addShop(shop3);
        parentCompany.addShop(shop4);
        List<Shop> shops = parentCompany.getShopByTerms("avignon", null);
        for(Shop shop : shops){
            parentCompany.removeShop(shop);
        }
        assertEquals(2, parentCompany.getShops().size());

        parentCompany.addShop(shop3);
        parentCompany.addShop(shop4);
        List<Shop> shops2 = parentCompany.getShopByTerms("avignon", "cookie miam");
        for(Shop shop : shops2){
            parentCompany.removeShop(shop);
        }
        assertEquals(3, parentCompany.getShops().size());

        parentCompany.addShop(shop4);
        List<Shop> shops3 = parentCompany.getShopByTerms(null, "cookie miam");
        for(Shop shop : shops3){
            parentCompany.removeShop(shop);
        }
        assertEquals(2, parentCompany.getShops().size());
    }
}
