package com.ibs.rest.base_operation;

import static io.restassured.RestAssured.given;

public class BaseOperation {
    public static void resetData() {
        given()
                .when()
                .post("api/data/reset")
                .then()
                .statusCode(200);
    }
}
