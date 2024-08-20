package com.ibs.tests.cucumber.steps;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PageManager;
import com.ibs.managers.PropManager;
import com.ibs.ui.pages.FoodPage;
import com.ibs.ui.pages.HomePage;
import com.ibs.ui.pages.ModalWindow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ibs.utils.PropConst.TEST_APP_URL;

public class TestAddGoodByUI {
    private final HomePage homePage = PageManager.getPageManager().getHomePage();
    private final FoodPage foodPage = PageManager.getPageManager().getFoodPage();
    private final ModalWindow modalWindow = PageManager.getPageManager().getModalWindow();

    @Given("I am on the Home page")
    public void iAmOnTheHomePage() {
        homePage.checkHomePageIsOpen();
    }

    @When("I navigate to the Goods page")
    public void iNavigateToTheGoodsPage() {
        homePage
                .clickSandBoxDropdown()
                .clickDropdownItemGoods()
                .checkFoodPageIsOpen()
                .checkTableColumnsNames();
    }

    @When("I click the button \"Добавить\"")
    public void iClickTheButton() {
        foodPage.clickButtonAdd();
    }

    @When("I see the modal window for adding a good")
    public void iSeeTheModalWindowForAddingAGood() {
        modalWindow
                .checkModalWindowIsDisplayed()
                .checkModalWindowElementsPresence();
    }

    @When("I fill in the name with {string}")
    public void iFillInTheNameWith(String name) {
        modalWindow.fillGoodName(name);
    }

    @When("I select the type {string}")
    public void iSelectTheType(String type) {
        modalWindow.selectType(type);
    }

    @When("I check the checkbox \"Экзотический\"")
    public void iCheckTheCheckbox() {
        modalWindow.selectCheckBoxExotic(true);
    }

    @When("I do not check the checkbox \"Экзотический\"")
    public void iDoNotCheckTheCheckbox() {
        modalWindow.selectCheckBoxExotic(false);
    }

    @When("I save the new good")
    public void iSaveTheNewGood() {
        modalWindow.saveNewGood();
    }

    @Then("the new good should be added to the FOOD table")
    public void theNewGoodShouldBeAddedToTheFoodTable() {
        foodPage.checkIfGoodAdded();
    }

    @Then("the last row should contain {string}, {string}, {string}")
    public void theLastRowShouldContain(String name, String type, String isExotic) {
        boolean shouldBeExotic = Boolean.parseBoolean(isExotic);
        foodPage.checkLastRowContent(name, type, shouldBeExotic);
    }
}

