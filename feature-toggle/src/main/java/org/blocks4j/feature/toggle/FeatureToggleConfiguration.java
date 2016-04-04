package org.blocks4j.feature.toggle;

import java.util.Map;
import java.util.Set;

public interface FeatureToggleConfiguration {

    Set<String> getEnabledFeatures();

    Map<String, Set<String>> getEnabledParameters();
}
