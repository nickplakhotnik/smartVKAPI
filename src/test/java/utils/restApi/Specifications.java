package utils.restApi;

import aquality.selenium.core.utilities.JsonSettingsFile;
import enums.ParamsEnum;
import io.restassured.RestAssured;
import model.RestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.TestUser;

public class Specifications {

    private static RestConfig config = new RestConfig(new JsonSettingsFile("restConfigs.json"));
    private static TestUser user = new TestUser(new JsonSettingsFile("user.json"));

    public static RequestSpecification requestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(config.getRestUrl())
                .addQueryParam(ParamsEnum.OWNER_ID.getParam(), user.getOwnerId())
                .addQueryParam(ParamsEnum.ACCESS_TOKEN.getParam(), config.getToken())
                .addQueryParam(ParamsEnum.VERSION.getParam(), config.getVersion())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void instalSpecification(RequestSpecification requestSpecification) {
        RestAssured.requestSpecification = requestSpecification;
    }
}
