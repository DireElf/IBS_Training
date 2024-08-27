package com.ibs.tests.rest.specifications;

import com.ibs.managers.PropManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

import static com.ibs.utils.constants.PropConst.API_FOOD_PATH;
import static com.ibs.utils.constants.PropConst.TEST_APP_URL;

public class AddGoodSpecification {
    private static final PropManager propManager = PropManager.getPropManager();

    /**
     * Creates and returns a RequestSpecification object configured with the base URI
     * and cookies required for making API requests.
     *
     * @return a configured RequestSpecification instance
     */
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(propManager.getProperty(TEST_APP_URL))
                .addCookies(getCookies())
                .build();
    }

    /**
     * Retrieves and returns cookies by sending a GET request to the API endpoint.
     *
     * @return the Cookies object containing the detailed cookies from the response
     */
    private static Cookies getCookies() {
        return RestAssured.given()
                .when()
                .get(propManager.getProperty(API_FOOD_PATH))
                .getDetailedCookies();
    }

}
