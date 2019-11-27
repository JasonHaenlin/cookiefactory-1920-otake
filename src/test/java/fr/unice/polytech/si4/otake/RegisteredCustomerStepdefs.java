package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import io.cucumber.java8.En;

public class RegisteredCustomerStepdefs implements En {

    final String[] idOfRegisteredCustomer = { null };
    Order o;

    public RegisteredCustomerStepdefs() {
        ParentCompany parentCompany = new ParentCompany();
        parentCompany.addShop("Nice", "Cookie Chief");
        // TODO

        Given("a not registered customer opening the application", () -> {
        });
        Given("a registered customer with id of {string}", (String id) -> {
            // parentCompany.addOrUpdateRegisteredCustomer(id);
            // idOfRegisteredCustomer[0] = id;
        });
        Given("an adherent to the fidelity program with id of {string}", (String id) -> {
            // parentCompany.addOrUpdateRegisteredCustomer(id, true);
            // idOfRegisteredCustomer[0] = id;
        });
        When("the adherent order {int} cookies", (Integer nbCookies) -> {
            // RegisteredCustomer r =
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // Shop s = parentCompany.getShops().get(0);
            // o = new Order();
            // for (int i = 0; i < nbCookies; i++)
            // o.addCookie(Recipe.SOOCHOCOLATE.create());
            // SimpleDate date = new SimpleDate("00-00-00 15:00");
            // o.setAppointmentDate(date);
            // s.addOrder(o, r);
        });
        Then("the adherent's cookiePoints increase", () -> {
            // RegisteredCustomer r =
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // assertTrue(r.getCookiePoints() > 0 && r.getCookiePoints() < 30);
        });
        Then("the adherent pays full price", () -> {
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // assertEquals(o.getQuantity() * Recipe.SOOCHOCOLATE.create().getPrice(),
            // o.getPriceWithoutTaxes());
        });
        Then("the adherent will get discount on next purchase", () -> {
            // RegisteredCustomer r =
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // assertEquals(RegisteredCustomer.QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT,
            // r.getCookiePoints());
        });
        Then("the adherent pays with {double} percent discount on their purchase", (Double discountPercent) -> {
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // double fullPrice = (o.getQuantity() *
            // Recipe.SOOCHOCOLATE.create().getPrice());
            // assertNotEquals(fullPrice, o.getPriceWithoutTaxes());
        });
        When("the registered customer choose to adhere to the fidelity program", () -> {
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]).setSubscribed(true);
        });
        Then("the registered customer is now part of the fidelity program", () -> {
            // assertTrue(parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]).getSubscribed());
        });
        Then("the adherent now has {int} cookiePoints in his account", (Integer cookiePoints) -> {
            // RegisteredCustomer r =
            // parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]);
            // assertEquals(r.getCookiePoints(), cookiePoints);
        });
        When("the customer creates an account", () -> {
            // parentCompany.addOrUpdateRegisteredCustomer();
        });
        Then("the customer is registered in the company database", () -> {
            // assertTrue(parentCompany.getRegisteredCustomers().size() > 0);
        });

    }
}
