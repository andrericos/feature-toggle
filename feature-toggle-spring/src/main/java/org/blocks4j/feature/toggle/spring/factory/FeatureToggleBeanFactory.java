package org.blocks4j.feature.toggle.spring.factory;

import org.blocks4j.feature.toggle.FeatureToggleConfiguration;
import org.blocks4j.feature.toggle.spring.exception.FeatureToggleBeanFactoryException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FeatureToggleBeanFactory<T> implements ApplicationContextAware {

    private static final String PROPERTY_PREFIX = "set";

    private FeatureToggleConfiguration config;

    private String onRef;

    private String offRef;

    private Class<T> commonInterface;

    private String featureName;

    private ApplicationContext context;

    public T toggle() {
        return org.blocks4j.feature.toggle.factory.FeatureToggleFactory
                .forFeature(this.config, this.getFeatureName(), this.commonInterface)
                .whenEnabled(this.getBeanOn())
                .whenDisabled(this.getBeanOff())
                .build();
    }

    public T getBeanOff() {
        return this.getContext().getBean(this.getOffRef(), this.commonInterface);
    }

    public T getBeanOn() {
        return this.getContext().getBean(this.getOnRef(), this.commonInterface);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
        FeatureToggleConfiguration config = context.getBean(FeatureToggleConfiguration.class);
        if (config == null) {
            throw new FeatureToggleBeanFactoryException("FeatureToggleConfiguration not found on context.");
        }
        this.config = config;
    }

    public FeatureToggleConfiguration getConfig() {
        return this.config;
    }

    public void setConfig(FeatureToggleConfiguration config) {
        this.config = config;
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getOnRef() {
        return this.onRef;
    }

    public void setOnRef(String onRef) {
        this.onRef = onRef;
    }

    public String getOffRef() {
        return this.offRef;
    }

    public void setOffRef(String offRef) {
        this.offRef = offRef;
    }

    public ApplicationContext getContext() {
        return this.context;
    }

    public Class<? super T> getCommonInterface() {
        return this.commonInterface;
    }

    public void setCommonInterface(Class<T> commonInterface) {
        this.commonInterface = commonInterface;
    }
}
