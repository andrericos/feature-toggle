package org.blocks4j.feature.toggle.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:feature_files/feature_toggle.feature"},
        glue = "org.blocks4j.feature.toggle.test",
        tags = {"~@Ignore"},
        strict = true,
        plugin = "pretty"
)
public class FeatureToggleTest {
}
