package org.blocks4j.feature.toggle.test;

import org.blocks4j.feature.toggle.FeatureToggleConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestingFeatureToggleConfiguration implements FeatureToggleConfiguration {

    private Set<String> enabledFeatures = new HashSet<>();

    private Map<String, Set<String>> enabledParameters = new HashMap<>();


    @Override
    public Set<String> getEnabledFeatures() {
        return this.enabledFeatures;
    }

    @Override
    public Map<String, Set<String>> getEnabledParameters() {
        return this.enabledParameters;
    }
}
