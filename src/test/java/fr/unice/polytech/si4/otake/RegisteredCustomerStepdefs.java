package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisteredCustomerStepdefs implements En {

    public RegisteredCustomerStepdefs() {

        ParentCompany parentCompany = new ParentCompany();

        Given("a not registered customer opening the application", ()->{});
        When("the customer creates an account",()->{
            parentCompany.addOrUpdateRegisteredCustomer();
        });
        Then("the customer is registered in the company database",()->{
            assertTrue(parentCompany.getRegisteredCustomers().size() > 0);
        });

    }
}
