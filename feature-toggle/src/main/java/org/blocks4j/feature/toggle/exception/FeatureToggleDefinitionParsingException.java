package org.blocks4j.feature.toggle.exception;

public class FeatureToggleDefinitionParsingException extends RuntimeException {

    public FeatureToggleDefinitionParsingException() {
        super();
    }

    public FeatureToggleDefinitionParsingException(String message) {
        super(message);
    }

    public FeatureToggleDefinitionParsingException(Throwable cause) {
        super(cause);
    }

    public FeatureToggleDefinitionParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}