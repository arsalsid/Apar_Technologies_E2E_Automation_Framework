package api.base;

import baseTest.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class BaseAPI {

    public static RequestSpecification setupRequest() throws IOException {
        return RestAssured.given()
                .baseUri(PropertyReader.getInstance().readProperty("baseURI"))
                .contentType("application/json");
    }
}
