package org.blocks4j.feature.toggle.spring.exception;

public class FeatureToggleBeanFactoryException extends RuntimeException {

    private static final long serialVersionUID = 3731403122516259088L;

    public FeatureToggleBeanFactoryException() {
        super();
    }

    public FeatureToggleBeanFactoryException(String message) {
        super(message);
    }

    public FeatureToggleBeanFactoryException(Throwable cause) {
        super(cause);
    }

    public FeatureToggleBeanFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}