package com.ibs.tests.rest.base_test;

import com.ibs.managers.RESTManager;
import com.ibs.rest.base_operation.BaseOperation;
import com.ibs.tests.rest.specifications.AddGoodSpecification;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected static final RESTManager restManager = RESTManager.getRESTManager();

    @Before
    public void setRequestSpec() {
        RestAssured.requestSpecification = AddGoodSpecification.getRequestSpec();
    }

    @After
    public void resetDataAfter() {
        BaseOperation.resetData();
    }
}
