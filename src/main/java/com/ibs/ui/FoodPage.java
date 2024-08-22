package com.ibs.ui;

import com.ibs.managers.PageManager;
import com.ibs.ui.base_page.BasePage;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.ibs.utils.WebDriverUtils.setExplicitlyWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FoodPage extends BasePage {
    @FindBy(tagName = "h5")
    private WebElement tableHeader;
    @FindBy(tagName = "table")
    private WebElement table;
    @FindBy(xpath = "//tr/th[text()='Наименование']")
    private WebElement goodName;
    @FindBy(xpath = "//tr/th[text()='Тип']")
    private WebElement goodType;
    @FindBy(xpath = "//tr/th[text()='Экзотический']")
    private WebElement isGoodExotic;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement modalContent;

    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//th[@scope='row']")
    private List<WebElement> tableRowsNumbers;

    private int tableRowsCount;

    /**
     * Checks if the Food page is open by verifying the visibility of the table header.
     * @return the current FoodPage instance
     */
    @Step
    public FoodPage checkFoodPageIsOpen() {
        assertTrue("Page /food isn't displayed", tableHeader.isDisplayed());
        return this;
    }

    /**
     * Validates the presence and correctness of the table columns on the Food page.
     * @return the current FoodPage instance
     */
    @Step
    public FoodPage checkTableColumnsNames() {
        assertTrue("Goods table isn't displayed", table.isDisplayed());
        assertEquals("Наименование", goodName.getText());
        assertEquals("Тип", goodType.getText());
        assertEquals("Экзотический", isGoodExotic.getText());
        return this;
    }

    /**
     * Clicks the "Add" button to open a modal window for adding a new item.
     * @return the ModalWindow instance representing the modal dialog
     */
    @Step
    public ModalWindow clickButtonAdd() {
        tableRowsCount = tableRowsNumbers.size();
        assertTrue("Button 'Добавить' isn't displayed", buttonAdd.isDisplayed());
        buttonAdd.click();
        setExplicitlyWait(5L).until(ExpectedConditions.visibilityOf(modalContent));
        return PageManager.getPageManager().getModalWindow();
    }

    /**
     * Checks if a new item has been added to the table by comparing the row count before and after refresh.
     * @return the current FoodPage instance
     */
    @Step
    public FoodPage checkIfGoodAdded() {
        driverManager.getWebDriver().navigate().refresh();
        setExplicitlyWait(5L).until(ExpectedConditions.visibilityOf(table));
        Assert.assertEquals(++tableRowsCount, tableRowsNumbers.size());
        return this;
    }

    /**
     * Verifies the content of the last row in a table based on the provided parameters.
     *
     * @param goodName the expected name of the good in the last row.
     * @param goodType the expected type of the good in the last row.
     * @param isExotic the expected exotic status of the good in the last row.
     * @return the current instance of the FoodPage.
     * @throws AssertionError if the content of the last row does not match the provided parameters.
     */
    @Step
    public FoodPage checkLastRowContent(String goodName, String goodType, boolean isExotic) {
        By lastAddedRowXpath = By
                .xpath(
                        "//th[@scope='row' and text()='" + tableRowsCount +"']/following-sibling::td"
                );
        List<WebElement> rowContent = driverManager.getWebDriver().findElements(lastAddedRowXpath);
        Assert.assertEquals("Good name isn't valid", goodName, rowContent.get(0).getText());
        Assert.assertEquals("Good type isn't valid", goodType, rowContent.get(1).getText());
        Assert.assertEquals("Exotic type isn't valid", isExotic,
                Boolean.valueOf(rowContent.get(2).getText()));
        return this;
    }
}
