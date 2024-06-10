package udemyframework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/udemyframework/cucumber",
        glue = "udemyframework.StepDefinitions",
        monochrome = true,
        tags = "@Regression",
        plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
