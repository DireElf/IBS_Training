package com.ibs.rest;

import com.ibs.models.GoodData;
import com.ibs.rest.base_operation.BaseOperation;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddGoodOperation extends BaseOperation {
    private int entriesCount;

    public AddGoodOperation setCurrentEntriesCount() {
        entriesCount = getAllGoodData().size();
        return this;
    }

    public AddGoodOperation executeAddGoodRequest(GoodData goodData) {
        given()
                .contentType("application/json")
                .body(goodData)
                .when()
                .post("api/food")
                .then()
                .statusCode(200);
        return this;
    }

    public AddGoodOperation checkEntriesCountAfterAddGood() {
        int expectedCount = ++entriesCount;
        int actualCount = getAllGoodData().size();
        Assert.assertEquals(String.format("Expected entries count %d, but actual %d", expectedCount, actualCount),
                expectedCount,
                actualCount);
        return this;
    }

    public AddGoodOperation checkAddedGoodPresence(GoodData goodData) {
        List<GoodData> goodList = getAllGoodData();
        Assert.assertTrue(goodList.contains(goodData));
        return this;
    }

    private List<GoodData> getAllGoodData() {
        return Arrays.asList(given()
                .when()
                .get("api/food")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(GoodData[].class));
    }
}
