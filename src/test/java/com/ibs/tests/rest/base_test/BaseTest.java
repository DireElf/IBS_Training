package com.ibs.tests.rest.base_test;

import com.ibs.managers.PropManager;
import com.ibs.managers.RESTManager;
import com.ibs.rest.base_operation.BaseOperation;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.ibs.utils.constants.PropConst.TEST_APP_URL;

public class BaseTest {
    private static final PropManager propManager = PropManager.getPropManager();
    protected static final RESTManager restManager = RESTManager.getRESTManager();

    @BeforeClass
    public static void resetDataBefore() {
        BaseOperation.resetData();
    }

    @Before
    public void setRequestSpec() {
        RestAssured.requestSpecification = getRequestSpec();
    }

    @AfterClass
    public static void resetDataAfter() {
        BaseOperation.resetData();
    }

    private static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(propManager.getProperty(TEST_APP_URL))
                .addCookies(getCookies())
                .build();
    }

    private static Cookies getCookies() {
        return RestAssured.given()
                .when()
                .get("/api/food")
                .getDetailedCookies();
    }
}
