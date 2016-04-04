package org.blocks4j.feature.toggle.test;

import org.blocks4j.feature.toggle.annotation.parameters.ParameterToggle;

public class OperationParameter {

    @ParameterToggle("paramTest")
    private String parameter;

    public OperationParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return this.parameter;
    }
}
