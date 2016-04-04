package org.blocks4j.feature.toggle.proxy;

import org.apache.commons.collections4.CollectionUtils;
import org.blocks4j.feature.toggle.FeatureToggleConfiguration;
import org.blocks4j.feature.toggle.parameters.ParametersToggleHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Feature<T> implements InvocationHandler {

    private ParametersToggleHandler paramters;
    private FeatureToggleConfiguration config;
    private Class<? super T> commonInterface;
    private String featureName;
    private T featureOff;
    private T featureOn;

    public void init() {
        this.paramters = new ParametersToggleHandler(this.config, this.commonInterface);
    }

    public void setName(String featureName) {
        this.featureName = featureName;
    }

    public void setOff(T featureOff) {
        this.featureOff = featureOff;
    }

    public void setOn(T featureOn) {
        this.featureOn = featureOn;
    }

    public void setCommonsInterface(Class<? super T> commonInterface) {
        this.commonInterface = commonInterface;
    }

    public void setConfig(FeatureToggleConfiguration config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object _this;
        if (this.isOn(method, args)) {
            _this = this.featureOn;
        } else {
            _this = this.featureOff;
        }
        try {
            return method.invoke(_this, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private boolean isOn(Method method, Object[] args) {
        if (this.isFeatureOnFeatureList()) {
            if (args != null) {
                return this.paramters.handle(method, args, this.featureName);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isFeatureOnFeatureList() {
        return CollectionUtils.isNotEmpty(this.config.getEnabledFeatures()) && this.config.getEnabledFeatures().contains(this.featureName);
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public T getFeatureOff() {
        return this.featureOff;
    }

    public T getFeatureOn() {
        return this.featureOn;
    }
}