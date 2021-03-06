package fr.unice.polytech.si4.otake.cookiefactory.discount;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.product.Pack;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.ProductType;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * Discount
 */
public class Discount implements Comparable<Discount> {
    private final DiscountBehaviour discBeh;
    private final DiscountTrigger discTrig;
    public final boolean exclusive;
    private double reduction;

    public Discount(boolean exclusive, double reduction, DiscountTrigger discTrig, DiscountBehaviour discBeh) {
        this.discBeh = discBeh;
        this.discTrig = discTrig;
        this.exclusive = exclusive;
        this.reduction = reduction;
    }

    public double applyIfEligible(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
        double red = 0;
        if (this.discTrig.check(order, registeredCustomer, shop)) {
            red = this.discBeh.apply(order, registeredCustomer, shop, reduction);
        }
        return red != 0. ? red : 0.;

    }

    /**
     * @param reduction the reduction to set
     */
    public void setReduction(double reduction) {
        this.reduction = reduction;
    }

    @Override
    public int compareTo(Discount d) {
        if (this.exclusive == d.exclusive || this.equals(d)) {
            return 0;
        }
        if (this.exclusive) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Discount)) {
            return false;
        }
        Discount d = (Discount) obj;
        return this.hashCode() == d.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.exclusive, this.reduction);
    }

    public static class Trigger {

        private Trigger() {
        }

        public static DiscountTrigger code(String code) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> order.getCode().equals(code);
        }

        public static DiscountTrigger codeStartWith(String code, List<String> endTerms) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                String c = order.getCode();
                if (c.startsWith(code)) {
                    for (String s : endTerms) {
                        if (c.endsWith(s)) {
                            return true;
                        }
                    }
                }
                return false;
            };
        }

        public static DiscountTrigger hour() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                int currentTime = order.getAppointmentDate().getHour();
                int closingTime = shop.getSchedule().getClosingHour();
                return closingTime - currentTime <= 1;
            };
        }

        public static DiscountTrigger seniority() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                if (registeredCustomer == null) {
                    return false;
                }
                int currentYear = new SimpleDate().getYear();
                int year = registeredCustomer.getRegistrationDate().getYear();
                return currentYear - year >= 1;
            };
        }

        public static DiscountTrigger fidelity(int nbBeforeDiscount) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop) -> {
                if (registeredCustomer == null) {
                    return false;
                }
                return registeredCustomer.getCookiePoints() >= nbBeforeDiscount;
            };
        }
    }

    public static class Behaviour {

        private Behaviour() {
        }

        public static DiscountBehaviour basic() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) -> reduction;
        }

        public static DiscountBehaviour enrolmentTime() {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) -> {
                int currentYear = new SimpleDate().getYear();
                int year = registeredCustomer.getRegistrationDate().getYear();
                return (currentYear - year) * reduction;
            };
        }

        public static DiscountBehaviour products(int min) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) -> {
                int count = 0;
                for (Map.Entry<Product, Integer> c : order.getContent().entrySet()) {
                    count += c.getValue();
                    if (count >= min) {
                        return reduction;
                    }
                }

                return 0;
            };
        }

        public static DiscountBehaviour customerPoints(int removePoints) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) -> {
                if (registeredCustomer == null) {
                    return 0.;
                }
                registeredCustomer.removePoints(removePoints);
                return reduction;
            };
        }

        public static DiscountBehaviour elligibleCookies(RecipeBook recipeBook) {
            return (Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) -> {
                double price = 0;
                double total = 0;
                List<Cookie> cookies = recipeBook.getCookies();
                for (Map.Entry<Product, Integer> c : order.getContent().entrySet()) {
                    Product p = c.getKey();
                    if (cookies.contains(p)) {
                        price += p.applyTaxes(shop.getTaxes()) * c.getValue();
                    }
                    if (p.isA(ProductType.PACK)) {
                        Pack pack = (Pack) p;
                        price += pack.getPackType().getPrice() * pack.getSize();
                    }
                    total += p.applyTaxes(shop.getTaxes()) * c.getValue();
                }
                return (reduction * price) / total;
            };
        }
    }

}
