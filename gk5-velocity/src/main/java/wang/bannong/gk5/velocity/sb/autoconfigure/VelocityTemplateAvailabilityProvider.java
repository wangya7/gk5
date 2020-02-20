package wang.bannong.gk5.velocity.sb.autoconfigure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.template.PathBasedTemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;

/**
 * {@link TemplateAvailabilityProvider} that provides availability information for
 * Velocity view templates.
 *
 * @author Andy Wilkinson
 * @since 1.1.0
 * 4.3
 */
public class VelocityTemplateAvailabilityProvider
        extends PathBasedTemplateAvailabilityProvider {

    public VelocityTemplateAvailabilityProvider() {
        super("org.apache.velocity.app.VelocityEngine",
                VelocityTemplateAvailabilityProperties.class, "spring.velocity");
    }

    static class VelocityTemplateAvailabilityProperties
            extends TemplateAvailabilityProperties {

        private List<String> resourceLoaderPath = new ArrayList<String>(
                Arrays.asList(VelocityProperties.DEFAULT_RESOURCE_LOADER_PATH));

        VelocityTemplateAvailabilityProperties() {
            super(VelocityProperties.DEFAULT_PREFIX, VelocityProperties.DEFAULT_SUFFIX);
        }

        @Override
        protected List<String> getLoaderPath() {
            return this.resourceLoaderPath;
        }

        public List<String> getResourceLoaderPath() {
            return this.resourceLoaderPath;
        }

        public void setResourceLoaderPath(List<String> resourceLoaderPath) {
            this.resourceLoaderPath = resourceLoaderPath;
        }

    }

}