package org.blocks4j.feature.toggle.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.SendProcessor;
import org.blocks4j.feature.toggle.FeatureToggleConfiguration;

public class ToggleFeatureRoute extends RouteBuilder {

    private final FeatureToggleConfiguration config;
    private final String featureName;
    private final String featureEndPoint;
    private final String alternateEndPoint;

    private ToggleFeatureRoute(FeatureToggleConfiguration config, String featureName, String featureEndPoint, String alternateEndPoint) {
        this.featureEndPoint = featureEndPoint;
        this.alternateEndPoint = alternateEndPoint;
        this.config = config;
        this.featureName = featureName;
    }

    @Override
    public void configure() throws Exception {
        this.getContext().addInterceptStrategy((context, definition, target, nextTarget) -> {
            if (nextTarget instanceof SendProcessor) {
                SendProcessor sendProcessor = (SendProcessor) nextTarget;
                if (sendProcessor.getDestination().getEndpointUri().equals(ToggleFeatureRoute.this.featureEndPoint)) {
                    Endpoint alternateEndpoint = context.getEndpoint(ToggleFeatureRoute.this.alternateEndPoint);
                    return new ToggledSendProcessor(ToggleFeatureRoute.this.config, ToggleFeatureRoute.this.featureName, sendProcessor.getDestination(), alternateEndpoint);
                }
            }
            return nextTarget;
        });
    }

    public static ToggleFeatureRouteBuilder createBuilder() {
        return new ToggleFeatureRouteBuilder();
    }

    public static class ToggleFeatureRouteBuilder {

        private FeatureToggleConfiguration config;
        private String featureName;
        private String featureOnEndpoint;
        private String featureOffEndpoint;

        private ToggleFeatureRouteBuilder() {

        }

        public ToggleFeatureRouteBuilder config(FeatureToggleConfiguration config) {
            this.config = config;
            return this;
        }

        public ToggleFeatureRouteBuilder featureName(String featureName) {
            this.featureName = featureName;
            return this;
        }

        public ToggleFeatureRouteBuilder featureOn(String featureOnEndpoint) {
            this.featureOnEndpoint = featureOnEndpoint;
            return this;
        }

        public ToggleFeatureRouteBuilder featureOff(String featureOffEndpoint) {
            this.featureOffEndpoint = featureOffEndpoint;
            return this;
        }

        public ToggleFeatureRoute build() {
            return new ToggleFeatureRoute(this.config, this.featureName, this.featureOnEndpoint, this.featureOffEndpoint);
        }
    }
}
