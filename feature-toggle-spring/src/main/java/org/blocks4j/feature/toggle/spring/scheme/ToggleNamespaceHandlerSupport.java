package org.blocks4j.feature.toggle.spring.scheme;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ToggleNamespaceHandlerSupport extends NamespaceHandlerSupport {

    public void init() {
        super.registerBeanDefinitionParser("toggle", new FeatureToggleDefinitionParser());
    }
}