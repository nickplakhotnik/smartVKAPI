package model;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestData {

    private String photo;
    private String difference;

    public TestData(JsonSettingsFile jsonSettingsFile) {
        photo = jsonSettingsFile.getValue("/photo").toString();
        difference = jsonSettingsFile.getValue("/difference").toString();
    }

    public String getPhoto() {
        return photo;
    }

    public String getDifference() {
        return difference;
    }
}
