package fr.unice.polytech.si4.otake.cookiefactory.discount;

import java.util.Calendar;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * Discount
 */
public class Discount implements Comparable<Discount> {
    private final DiscountBehaviour discBeh;
    private final DiscountTrigger discTrig;
    public final boolean exclusive;
    private double reduction;

    Discount(boolean exclusive, double reduction, DiscountTrigger discTrig, DiscountBehaviour discBeh) {
        this.discBeh = discBeh;
        this.discTrig = discTrig;
        this.exclusive = exclusive;
        this.reduction = reduction;
    }

    double applyIfEligible(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
        if (this.discTrig.check(order, registeredCustomer, shop)
                && this.discBeh.apply(order, registeredCustomer, shop)) {
            return this.reduction;
        }
        return 0;

    }

    /**
     * @param reduction the reduction to set
     */
    public void setReduction(double reduction) {
        this.reduction = reduction;
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

        private Trigger() {
        }

        public static DiscountTrigger code(String code) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> order.getCode().equals(code);

        }

        public static DiscountTrigger hour() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                int currentTime = order.getAppointmentDate().get(Calendar.HOUR_OF_DAY);
                int closingTime = shop.getSchedule().getClosing().get(Calendar.HOUR_OF_DAY);
                return closingTime - currentTime <= 1;
            };
        }

        public static DiscountTrigger seniority() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int year = registeredCustomer.getRegistrationDate().get(Calendar.YEAR);
                return currentYear - year >= 1;
            };
        }
    }

    public static class Behaviour {

        private Behaviour() {
        }

        public static DiscountBehaviour basic() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> true;
        }

        public static DiscountBehaviour products(int min) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                for (Map.Entry<Cookie, Integer> c : order.getTheOrderContent().entrySet()) {
                    if (c.getValue() >= min) {
                        return true;
                    }
                }
                return false;
            };

        }
    }

}
