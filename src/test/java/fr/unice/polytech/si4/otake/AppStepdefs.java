package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java8.En;

public class AppStepdefs implements En {

    App app;

    public AppStepdefs() {

        Given("an App", () -> {
            app = new App();
        });
        Then("Say {string}", (String greet) -> {
            assertEquals(greet, app.getGreeting());
        });
        When("App greeting", () -> {
            String greeting = app.getGreeting();
            assertTrue(!greeting.isEmpty());
        });
    }

}
