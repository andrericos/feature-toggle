package org.blocks4j.feature.toggle.test;

import org.blocks4j.feature.toggle.annotation.parameters.ParameterToggle;

public interface TestingFeature {

    void operation(OperationParameter operationParameter);

    void operation(@ParameterToggle("paramTest") String operationParameter);

}
