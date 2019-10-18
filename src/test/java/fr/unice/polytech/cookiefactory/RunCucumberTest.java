package fr.unice.polytech.cookiefactory;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(value = Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, features = "src/test/resources/features")
public class RunCucumberTest {
    // will run all features found on the classpath in the same package as this
    // class
}
