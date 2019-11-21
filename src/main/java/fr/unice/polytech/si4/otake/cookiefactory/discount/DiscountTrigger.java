package fr.unice.polytech.si4.otake.cookiefactory.discount;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * DiscountTrigger
 */
public interface DiscountTrigger {
    boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop);
}
