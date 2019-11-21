package fr.unice.polytech.si4.otake.cookiefactory.discount;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * DiscountQueue
 */
public class DiscountQueue {

    private final Queue<Discount> discounts;

    DiscountQueue() {
        this.discounts = new PriorityQueue<>(10, new Comparator<Discount>() {
            @Override
            public int compare(Discount d1, Discount d2) {
                return d1.compareTo(d2);
            }
        });
    }

    boolean add(Discount discount) {
        if (discount == null) {
            return false;
        }
        return this.discounts.add(discount);
    }

    void applyDiscounts(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
        for (Discount discount : discounts) {
            if (discount.applyIfEligible(order, registeredCustomer, shop) && discount.exclusive) {
                break;
            }
        }
    }
}
