package fr.unice.polytech.si4.otake.cookiefactory.discount;

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
        this.discounts = new PriorityQueue<>(10, (Discount d1, Discount d2) -> d1.compareTo(d2));
    }

    boolean add(Discount discount) {
        if (discount == null) {
            return false;
        }
        return this.discounts.add(discount);
    }

    void applyDiscounts(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
        double reductions = 0;
        double nr;
        for (Discount discount : discounts) {
            nr = discount.applyIfEligible(order, registeredCustomer, shop);
            reductions += nr;
            if (nr != 0.0 && discount.exclusive) {
                break;
            }
        }
        order.applyDiscount(reductions);
    }
}
