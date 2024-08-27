package com.ibs.tests.rest;

import com.ibs.models.GoodData;
import com.ibs.tests.rest.base_test.BaseTest;
import com.ibs.utils.TestDataUtils;
import org.junit.Test;

public class TestAddGoodAPI extends BaseTest {
    private final GoodData fruitExotic = new GoodData(
            TestDataUtils.getGoodFromJsonFile("pineapple.json")
    );

    @Test
    public void testAddGood() {
        restManager.getAddGoodOperation()
                .setCurrentEntriesCount()
                .executeAddGoodRequest(fruitExotic)
                .checkEntriesCountAfterAddGood()
                .checkAddedGoodPresence(fruitExotic);
    }
}
