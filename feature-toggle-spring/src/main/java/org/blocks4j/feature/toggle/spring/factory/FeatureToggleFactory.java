package org.blocks4j.feature.toggle.spring.factory;

import org.blocks4j.feature.toggle.FeatureToggleConfiguration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;


public class FeatureToggleFactory<T> implements FactoryBean<T> {

    @Autowired
    private ApplicationContext applicationContext;

    private String featureName;
    private FeatureToggleConfiguration config;
    private Class<T> commonInterface;
    private T featureOn;
    private T featureOff;

    private T instantiatedFeatureOn;
    private T instantiatedFeatureOff;

    private FeatureToggleFactory(FeatureToggleConfiguration config, String featureName, Class<T> commonInterface, T featureOn, T featureOff) {
        this.config = config;
        this.featureName = featureName;
        this.commonInterface = commonInterface;
        this.featureOn = featureOn;
        this.featureOff = featureOff;
    }

    @PostConstruct
    private void initFeatures() {
        this.instantiatedFeatureOn = this.instantiateBean(this.featureOn);
        this.instantiatedFeatureOff = this.instantiateBean(this.featureOff);
    }

    @SuppressWarnings("unchecked")
    private T instantiateBean(T bean) {
        this.applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
        return (T) this.applicationContext.getAutowireCapableBeanFactory().initializeBean(bean, "bean");
    }

    @Override
    public T getObject() throws Exception {
        return org.blocks4j.feature.toggle.factory.FeatureToggleFactory
                .forFeature(this.config, this.featureName, this.commonInterface)
                .whenEnabled(this.instantiatedFeatureOn)
                .whenDisabled(this.instantiatedFeatureOff)
                .build();
    }

    @Override
    public Class<? super T> getObjectType() {
        return this.commonInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static <W> FeatureToggleFactoryBuilder<W> createBuilder() {
        return new FeatureToggleFactoryBuilder<>();
    }

    public static class FeatureToggleFactoryBuilder<T> {

        private FeatureToggleConfiguration config;
        private String featureName;
        private Class<T> commonInterface;
        private T featureOn;
        private T featureOff;

        private FeatureToggleFactoryBuilder() {

        }

        public FeatureToggleFactoryBuilder<T> config(FeatureToggleConfiguration config) {
            this.config = config;
            return this;
        }

        public FeatureToggleFactoryBuilder<T> featureName(String featureName) {
            this.featureName = featureName;
            return this;
        }

        public FeatureToggleFactoryBuilder<T> commonInterface(Class<T> commonInterface) {
            this.commonInterface = commonInterface;
            return this;
        }

        public FeatureToggleFactoryBuilder<T> featureOn(T featureOn) {
            this.featureOn = featureOn;
            return this;
        }

        public FeatureToggleFactoryBuilder<T> featureOff(T featureOff) {
            this.featureOff = featureOff;
            return this;
        }

        public FeatureToggleFactory<T> build() {
            return new FeatureToggleFactory<>(this.config, this.featureName, this.commonInterface, this.featureOn, this.featureOff);
        }
    }
}
