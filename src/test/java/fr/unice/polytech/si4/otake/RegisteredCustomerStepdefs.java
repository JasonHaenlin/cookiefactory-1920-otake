package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisteredCustomerStepdefs implements En {

    public RegisteredCustomerStepdefs() {

        ParentCompany parentCompany = new ParentCompany();
        final String[] idOfRegisteredCustomer = {null};

        Given("a not registered customer opening the application", ()->{});
        Given("a registered customer with id of {string}", (String id)->{
            parentCompany.addOrUpdateRegisteredCustomer(id);
            idOfRegisteredCustomer[0] = id;
        });
        When("the registered customer choose to adhere to the fidelity program",()->{
            parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]).setSubscribed(true);
        });
        Then("the registered customer is now part of the fidelity program", ()->{
            assertTrue(parentCompany.getRegisteredCustomer(idOfRegisteredCustomer[0]).getSubscribed());
        });
        When("the customer creates an account",()->{
            parentCompany.addOrUpdateRegisteredCustomer();
        });
        Then("the customer is registered in the company database",()->{
            assertTrue(parentCompany.getRegisteredCustomers().size() > 0);
        });

    }
}
