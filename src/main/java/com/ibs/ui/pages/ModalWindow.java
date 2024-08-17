package com.ibs.ui.pages;

import com.ibs.models.Good;
import com.ibs.ui.pages.base_page.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.ibs.ui.pages.ui_utils.WaitUtils.setExplicitlyWait;

public class ModalWindow extends BasePage {
    @FindBy(xpath = "//h5[@class='modal-title']")
    private WebElement modalWindowTitle;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInputField;

    @FindBy(xpath = "//select[@id='type']")
    private WebElement goodTypeDropdown;

    @FindBy(xpath = "//input[@id='exotic']")
    private WebElement checkBoxIsExotic;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement modalContent;

    @FindBy(xpath = "//button[@id='save']")
    private WebElement buttonSave;

    /**
     * Checks if the modal window is displayed by verifying the visibility of its title.
     * @return the current ModalWindow instance
     */
    public ModalWindow checkModalWindowIsDisplayed() {
        Assert.assertTrue("Modal window isn't displayed", modalWindowTitle.isDisplayed());
        return this;
    }

    /**
     * Validates the presence of all essential elements within the modal window.
     * @return the current ModalWindow instance
     */
    public ModalWindow checkModalWindowElementsPresence() {
        Assert.assertTrue("Name input field isn't displayed", nameInputField.isDisplayed());
        Assert.assertTrue("Good type dropdown isn't displayed", goodTypeDropdown.isDisplayed());
        Assert.assertTrue("Checkbox 'Экзотический' isn't displayed", checkBoxIsExotic.isDisplayed());
        Assert.assertTrue("Button 'Сохранить' isn't displayed", buttonSave.isDisplayed());
        return this;
    }

    /**
     * Fills in the name of the good in the modal window's input field.
     * @param good the Good object containing the name to be entered
     * @return the current ModalWindow instance
     */
    public ModalWindow fillGoodName(Good good) {
        nameInputField.sendKeys(good.getName());
        return this;
    }

    /**
     * Selects the type of good from the dropdown menu in the modal window.
     * @param good the Good object containing the type to be selected
     * @return the current ModalWindow instance
     */
    public ModalWindow selectType(Good good) {
        goodTypeDropdown.click();
        WebElement selectedElement = goodTypeDropdown.findElement(By.xpath(
                "//select[@id='type']/option[text()='" + good.getType().getValue() + "']"
        ));
        setExplicitlyWait(5L).until(ExpectedConditions.elementToBeClickable(selectedElement));
        selectedElement.click();
        return this;
    }

    /**
     * Selects the "Exotic" checkbox if the good is exotic, ensuring it's selected correctly.
     * @param good the Good object containing the exotic status
     * @return the current ModalWindow instance
     */
    public ModalWindow selectCheckBoxExotic(Good good) {
        Assert.assertFalse("Checkbox 'Экзотический' is selected already", checkBoxIsExotic.isSelected());
        if (good.isExotic()) {
            checkBoxIsExotic.click();
            Assert.assertTrue("Checkbox 'Экзотический' isn't selected", checkBoxIsExotic.isSelected());
        }
        return this;
    }

    /**
     * Saves the new good by clicking the "Save" button and waits for the modal window to close.
     * @return the FoodPage instance representing the Food page
     */
    public FoodPage saveNewGood() {
        buttonSave.click();
        setExplicitlyWait(5L).until(ExpectedConditions.invisibilityOf(modalContent));
        return pageManager.getFoodPage();
    }
}
