package com.ibs.tests.cucumber.steps;

import com.ibs.db.operations.AddGoodOperation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;
public class TestAddGoodToDB {
    private AddGoodOperation addGoodOperation;

    @Given("the database connection is established")
    public void iEstablishDatabaseConnection() {
        addGoodOperation = new AddGoodOperation();
    }

    @When("I retrieve the number of rows in the FOOD table before adding a good")
    public void iRetrieveRowsNumberBeforeAddGood() throws SQLException {
        addGoodOperation.getRowsNumberBeforeAddGood();
    }

    @When("I add a new good to the FOOD table")
    public void iAddNewGoodToFoodTable() {
        addGoodOperation.addGood();
    }

    @Then("the number of rows in the FOOD table should increase by 1")
    public void iVerifyRowsNumberAfterAddGood() throws SQLException {
        addGoodOperation.checkRowsNumberAfterAddGood();
    }

    @Then("the last entry in the FOOD table should match the added good")
    public void iVerifyLastEntryValues() throws SQLException {
        addGoodOperation.checkLastEntryValues();
    }

    @Given("I have added a new good to the FOOD table")
    public void iAddGoodToFoodTable() throws SQLException {
        addGoodOperation.getRowsNumberBeforeAddGood();
        addGoodOperation.addGood();
    }

    @When("I remove the last added good from the FOOD table")
    public void iRemoveLastAddedGoodFromFoodTable() throws SQLException {
        addGoodOperation.removeLastEntry();
    }

    @Then("the number of rows in the FOOD table should decrease by 1")
    public void iVerifyRowsNumberAfterRemoveEntry() throws SQLException {
        addGoodOperation.checkRowsNumberAfterRemoveEntry();
    }

    @Then("the last added good should not exist in the FOOD table")
    public void iVerifyEntryDeletionById() throws SQLException {
        addGoodOperation.checkEntryDeletionById();
    }
}
