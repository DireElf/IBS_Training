package com.ibs.rest;

import com.ibs.models.GoodData;
import com.ibs.rest.base_operation.BaseOperation;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static com.ibs.utils.constants.PropConst.API_FOOD_PATH;
import static io.restassured.RestAssured.given;

public class AddGoodOperation extends BaseOperation {
    private int entriesCount;

    /**
     * Sets the current number of entries from the good data list.
     * This count is used for validation in subsequent operations.
     *
     * @return the current instance of AddGoodOperation
     */
    @Step
    public AddGoodOperation setCurrentEntriesCount() {
        entriesCount = getAllGoodData().size();
        return this;
    }

    /**
     * Executes a POST request to add a new good entry.
     *
     * @param goodData the data of the good to be added
     * @return the current instance of AddGoodOperation
     */
    @Step
    public AddGoodOperation executeAddGoodRequest(GoodData goodData) {
        given()
                .contentType(ContentType.JSON)
                .body(goodData)
                .when()
                .post(propManager.getProperty(API_FOOD_PATH))
                .then()
                .statusCode(200);
        return this;
    }

    /**
     * Checks that the number of entries has increased by one after adding a new good.
     *
     * @return the current instance of AddGoodOperation
     */
    @Step
    public AddGoodOperation checkEntriesCountAfterAddGood() {
        int expectedCount = ++entriesCount;
        int actualCount = getAllGoodData().size();
        Assert.assertEquals(String.format("Expected entries count %d, but actual %d", expectedCount, actualCount),
                expectedCount,
                actualCount);
        return this;
    }

    /**
     * Verifies that the added good is present in the list of all goods.
     *
     * @param goodData the data of the good that should be present
     * @return the current instance of AddGoodOperation
     */
    @Step
    public AddGoodOperation checkAddedGoodPresence(GoodData goodData) {
        List<GoodData> goodList = getAllGoodData();
        Assert.assertTrue(goodList.contains(goodData));
        return this;
    }

    /**
     * Retrieves all good data entries via a GET request.
     *
     * @return a list of all good data entries
     */
    private List<GoodData> getAllGoodData() {
        return Arrays.asList(given()
                .when()
                .get(propManager.getProperty(API_FOOD_PATH))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(GoodData[].class));
    }
}
