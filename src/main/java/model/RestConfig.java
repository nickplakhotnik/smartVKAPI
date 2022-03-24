package model;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class RestConfig {

    private String token;
    private String version;
    private String restUrl;

    public RestConfig(JsonSettingsFile jsonSettingsFile) {
        token = jsonSettingsFile.getValue("/token").toString();
        version = jsonSettingsFile.getValue("/version").toString();
        restUrl = jsonSettingsFile.getValue("/resturl").toString();
    }

    public String getToken() {
        return token;
    }

    public String getVersion() {
        return version;
    }

    public String getRestUrl() {
        return restUrl;
    }
}
