package model;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestUser {

    private String login;
    private String password;
    private String ownerId;

    public TestUser(JsonSettingsFile jsonSettingsFile) {
        login = jsonSettingsFile.getValue("/login").toString();
        password = jsonSettingsFile.getValue("/password").toString();
        ownerId = jsonSettingsFile.getValue("/owner_id").toString();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
