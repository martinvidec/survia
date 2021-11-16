package at.videc.survia.node.domain.dto;

import at.videc.survia.node.domain.model.constants.ApplicationStatus;

public class ApplicationResponse {
    private ApplicationStatus applicationStatus;
    private String apiVersion;
    private String authentication;

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
