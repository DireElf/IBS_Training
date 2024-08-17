package com.ibs.tests.ui;

import com.ibs.models.Good;
import com.ibs.models.enums.GoodType;
import com.ibs.tests.ui.base_test.BaseTest;
import org.junit.Test;

public class TestAddGood extends BaseTest {
    private final Good fruitExotic = new Good("Ананас", GoodType.FRUIT, true);
    private final Good vegetableNonExotic = new Good("Морковь", GoodType.VEGETABLE, false);

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
                .fillGoodName(fruitExotic)
                .selectType(fruitExotic)
                .selectCheckBoxExotic(fruitExotic)
                .saveNewGood()
                .checkIfGoodAdded()
                .checkLastRowContent(fruitExotic);

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
                .fillGoodName(vegetableNonExotic)
                .selectType(vegetableNonExotic)
                .selectCheckBoxExotic(vegetableNonExotic)
                .saveNewGood()
                .checkIfGoodAdded()
                .checkLastRowContent(vegetableNonExotic);
    }
}
