package at.videc.survia.node.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "at.videc.survia.node.info")
public class InfoProperties {

    private String version;
    private String apiVersion;
    private String apiTitle;
    private String apiDescription;
    private String apiLicense;
    private String apiLicenseUrl;
    private String apiExternalDocDescription;
    private String apiExternalDocUrl;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiTitle() {
        return apiTitle;
    }

    public void setApiTitle(String apiTitle) {
        this.apiTitle = apiTitle;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public String getApiLicense() {
        return apiLicense;
    }

    public void setApiLicense(String apiLicense) {
        this.apiLicense = apiLicense;
    }

    public String getApiLicenseUrl() {
        return apiLicenseUrl;
    }

    public void setApiLicenseUrl(String apiLicenseUrl) {
        this.apiLicenseUrl = apiLicenseUrl;
    }

    public String getApiExternalDocDescription() {
        return apiExternalDocDescription;
    }

    public void setApiExternalDocDescription(String apiExternalDocDescription) {
        this.apiExternalDocDescription = apiExternalDocDescription;
    }

    public String getApiExternalDocUrl() {
        return apiExternalDocUrl;
    }

    public void setApiExternalDocUrl(String apiExternalDocUrl) {
        this.apiExternalDocUrl = apiExternalDocUrl;
    }
}
