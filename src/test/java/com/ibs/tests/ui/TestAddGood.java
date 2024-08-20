package com.ibs.tests.ui;

import com.ibs.models.Good;
import com.ibs.models.enums.GoodType;
import com.ibs.tests.ui.base_test.BaseTest;
import com.ibs.utils.TestDataUtils;
import org.junit.Test;

public class TestAddGood extends BaseTest {
    private final Good fruitExotic = TestDataUtils.getGoodFromJsonFile("pineapple.json");
    private final Good vegetableNonExotic = TestDataUtils.getGoodFromJsonFile("carrot.json");

    @Test
    public void testAddFruitExotic() {
        pageManager.getHomePage()
                .checkHomePageIsOpen()
                .clickSandBoxDropdown()
                .clickDropdownItemGoods()
                .checkFoodPageIsOpen()
                .checkTableColumnsNames()
                .clickButtonAdd()
                .checkModalWindowIsDisplayed()
                .checkModalWindowElementsPresence()
                .fillGoodName(fruitExotic.getName())
                .selectType(fruitExotic.getType().getValue())
                .selectCheckBoxExotic(fruitExotic.isExotic())
                .saveNewGood()
                .checkIfGoodAdded()
                .checkLastRowContent(fruitExotic.getName(),
                                     fruitExotic.getType().getValue(),
                                     fruitExotic.isExotic());

    }

    @Test
    public void testAddVegetableNonExotic() {
        pageManager.getHomePage()
                .checkHomePageIsOpen()
                .clickSandBoxDropdown()
                .clickDropdownItemGoods()
                .checkFoodPageIsOpen()
                .checkTableColumnsNames()
                .clickButtonAdd()
                .checkModalWindowIsDisplayed()
                .checkModalWindowElementsPresence()
                .fillGoodName(vegetableNonExotic.getName())
                .selectType(vegetableNonExotic.getType().getValue())
                .selectCheckBoxExotic(vegetableNonExotic.isExotic())
                .saveNewGood()
                .checkIfGoodAdded()
                .checkLastRowContent(vegetableNonExotic.getName(),
                                     vegetableNonExotic.getType().getValue(),
                                     vegetableNonExotic.isExotic());
    }
}
