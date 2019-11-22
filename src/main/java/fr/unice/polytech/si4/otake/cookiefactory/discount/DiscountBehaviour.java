package fr.unice.polytech.si4.otake.cookiefactory.discount;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * DiscountApply
 */
public interface DiscountBehaviour {
    boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop);
}
