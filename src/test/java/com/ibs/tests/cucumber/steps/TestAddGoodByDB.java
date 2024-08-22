package com.ibs.tests.cucumber.steps;

import com.ibs.db.AddGoodOperation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;
public class TestAddGoodByDB {
    private AddGoodOperation addGoodOperation;

    @Given("the database connection is established")
    public void iEstablishDatabaseConnection() {
        addGoodOperation = new AddGoodOperation();
    }

    @When("I retrieve the number of rows in the FOOD table before adding a good")
    public void iRetrieveRowsNumberBeforeAddGood() throws SQLException {
        addGoodOperation.getRowsNumberBeforeAddGood();
    }

    @When("I add a new good with name {string}, type {string} and isExotic value {string} to the FOOD table")
    public void iAddNewGoodToFoodTable(String name, String type, String isExotic) {
        Boolean isExoticBoolean = Boolean.parseBoolean(isExotic);
        addGoodOperation.addGood(name, type, isExoticBoolean);
    }

    @Then("the number of rows in the FOOD table should increase by 1")
    public void iVerifyRowsNumberAfterAddGood() throws SQLException {
        addGoodOperation.checkRowsNumberAfterAddGood();
    }

    @Then("the last entry in the FOOD table should have values {string}, {string} and {string}")
    public void iVerifyLastEntryValues(String name, String type, String isExotic) throws SQLException {
        Boolean isExoticBoolean = Boolean.parseBoolean(isExotic);
        addGoodOperation.checkLastEntryValues(name, type, isExoticBoolean);
    }
}
