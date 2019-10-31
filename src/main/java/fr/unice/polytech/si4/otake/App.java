package fr.unice.polytech.si4.otake;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);

    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        logger.info(new App().getGreeting());
    }
}
