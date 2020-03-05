package wang.bannong.gk5.velocity.sb.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import wang.bannong.gk5.velocity.sf.web.VelocityViewResolver;

@ConfigurationProperties(prefix = "spring.velocity")
public class VelocityProperties extends AbstractTemplateViewResolverProperties {

    public static final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";

    public static final String DEFAULT_PREFIX = "";

    public static final String DEFAULT_SUFFIX = ".vm";

    private String dateToolAttribute;

    private String numberToolAttribute;

    private Map<String, String> properties = new HashMap<String, String>();

    private String resourceLoaderPath = DEFAULT_RESOURCE_LOADER_PATH;

    private String toolboxConfigLocation;

    private boolean preferFileSystemAccess = true;

    public VelocityProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    public String getDateToolAttribute() {
        return this.dateToolAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute) {
        this.dateToolAttribute = dateToolAttribute;
    }

    public String getNumberToolAttribute() {
        return this.numberToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute) {
        this.numberToolAttribute = numberToolAttribute;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getResourceLoaderPath() {
        return this.resourceLoaderPath;
    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        this.resourceLoaderPath = resourceLoaderPath;
    }

    public String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    public boolean isPreferFileSystemAccess() {
        return this.preferFileSystemAccess;
    }

    public void setPreferFileSystemAccess(boolean preferFileSystemAccess) {
        this.preferFileSystemAccess = preferFileSystemAccess;
    }

    @Override
    public void applyToMvcViewResolver(Object viewResolver) {
        // super.applyToViewResolver(viewResolver);
        super.applyToMvcViewResolver(viewResolver);
        VelocityViewResolver resolver = (VelocityViewResolver) viewResolver;
        resolver.setToolboxConfigLocation(getToolboxConfigLocation());
        resolver.setDateToolAttribute(getDateToolAttribute());
        resolver.setNumberToolAttribute(getNumberToolAttribute());
    }

}