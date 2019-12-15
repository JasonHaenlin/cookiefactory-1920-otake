package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Scheduler;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

/**
 * ShopAdjustTheScheduleStepdefs
 */
public class ShopAdjustTheScheduleStepdefs implements En {

    Shop shop;
    Scheduler sch;
    int op;
    int cl;

    public ShopAdjustTheScheduleStepdefs() {
        Before(() -> {
            shop = new Shop("city", "name", new ParentCompany());
        });

        Given("I setted the opening to {int} o'clock and the closing at {int} o'clock", (Integer op, Integer cl) -> {
            shop.withSchedule(op, cl);
        });

        When("I realized that {int} hours wasn't enough", (Integer period) -> {
            Scheduler sch = shop.getSchedule();
            assertEquals(period, sch.getClosingHour() - sch.getOpeningHour());
        });

        Then("I change the opening to {int} o'clock", (Integer op) -> {
            this.op = op;
            shop.getSchedule().setOpeningHour(op);
        });

        And("I change the closing to {int} o'clock", (Integer cl) -> {
            this.cl = cl;
            shop.getSchedule().setClosingHour(cl);
        });

        When("I am looking at the shop schedule", () -> {
            sch = shop.getSchedule();
        });

        Then("I can see the updated hours", () -> {
            assertEquals(this.op, sch.getOpeningHour());
            assertEquals(this.cl, sch.getClosingHour());
        });
    }
}
