package com.ibs.rest.base_operation;

import com.ibs.managers.PropManager;

import static com.ibs.utils.constants.PropConst.API_RESET_PATH;
import static io.restassured.RestAssured.given;

public class BaseOperation {
    protected static PropManager propManager = PropManager.getPropManager();

    /**
     * Resets the data by sending a POST request to the reset API endpoint.
     */
    public static void resetData() {
        given()
                .when()
                .post(propManager.getProperty(API_RESET_PATH))
                .then()
                .statusCode(200);
    }
}
