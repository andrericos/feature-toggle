package org.blocks4j.feature.toggle.exception;

public class FeatureToggleFactoryException extends RuntimeException {

    private static final long serialVersionUID = 3731403122516259088L;

    public FeatureToggleFactoryException() {
        super();
    }

    public FeatureToggleFactoryException(String message) {
        super(message);
    }

    public FeatureToggleFactoryException(Throwable cause) {
        super(cause);
    }

    public FeatureToggleFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}