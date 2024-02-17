package com.inicions.tasks.infrastructure.response;

public class HealthResponse {
    private String message;
    private String applicationName;

    private String version;

    public HealthResponse(String message, String applicationName, String version) {
        this.message = message;
        this.applicationName = applicationName;
        this.version = version;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getVersion(){
        return  version;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
