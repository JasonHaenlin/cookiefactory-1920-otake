package fr.unice.polytech.si4.otake.cookiefactory.discount;

import java.util.Calendar;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * Discount
 */
public class Discount implements Comparable<Discount> {
    private final DiscountBehaviour discBeh;
    private final DiscountTrigger discTrig;
    private final float reduction;
    public final boolean exclusive;

    Discount(boolean exclusive, float reduction, DiscountTrigger discTrig, DiscountBehaviour discBeh) {
        this.discBeh = discBeh;
        this.discTrig = discTrig;
        this.exclusive = exclusive;
        this.reduction = reduction;
    }

    boolean applyIfEligible(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
        if (this.discTrig.check(order, registeredCustomer, shop)) {
            return this.discBeh.apply(order, registeredCustomer, shop, reduction);
        }
        return false;
    }

    @Override
    public int compareTo(Discount d) {
        if (this.exclusive == d.exclusive) {
            return 0;
        }
        if (this.exclusive) {
            return -1;
        }
        return 1;

    }

    public static class Trigger {
        public static DiscountTrigger code(String code) {
            return new DiscountTrigger() {
                @Override
                public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                    return order.getCode().equals(code);
                }
            };
        }

        public static DiscountTrigger hour() {
            return new DiscountTrigger() {
                @Override
                public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                    int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int closingTime = shop.getSchedule().getClosing().get(Calendar.HOUR_OF_DAY);
                    return closingTime - currentTime <= 1;
                }
            };
        }

        public static DiscountTrigger seniority() {
            return new DiscountTrigger() {
                @Override
                public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int year = registeredCustomer.getRegistrationDate().get(Calendar.YEAR);
                    return currentYear - year >= 1;
                }
            };
        }
    }

    public static class Behaviour {
        public static DiscountBehaviour basic() {
            return new DiscountBehaviour() {
                @Override
                public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, float reduction) {
                    float price = order.getPriceWithTaxes();
                    order.setPriceWithTaxes((float) (price - (price * reduction)));
                    return true;
                }
            };

        }

        public static DiscountBehaviour products(int min) {
            return new DiscountBehaviour() {
                @Override
                public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, float reduction) {
                    return order.getQuantity() >= min;
                }
            };

        }
    }

}
