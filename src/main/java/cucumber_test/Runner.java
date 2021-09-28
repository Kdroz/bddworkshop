package cucumber_test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = { "classpath:features" },
        glue = {"cucumber_test/step_definitions"}
    )
public class Runner {
}
