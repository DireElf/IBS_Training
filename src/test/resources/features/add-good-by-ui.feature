# encoding: utf-8
Feature: Add goods to the FOOD table by UI (Cucumber)

  Background:
    Given I am on the Home page

  @test-by-UI
  Scenario Outline: Add an exotic fruit to the FOOD table
    * I navigate to the Goods page
    * I click the button "Добавить"
    * I see the modal window for adding a good
    * I fill in the name with <name>
    * I select the type <type>
    * I check the checkbox "Экзотический"
    * I save the new good
    * the new good should be added to the FOOD table
    * the last row should contain <name>, <type>, <isExotic>

    Examples:
      | name     | type    | isExotic |
      | "Ананас" | "Фрукт" | "true"   |


  @test-by-UI
  Scenario Outline: Add a non-exotic vegetable to the FOOD table
    * I navigate to the Goods page
    * I click the button "Добавить"
    * I see the modal window for adding a good
    * I fill in the name with <name>
    * I select the type <type>
    * I do not check the checkbox "Экзотический"
    * I save the new good
    * the new good should be added to the FOOD table
    * the last row should contain <name>, <type>, <isExotic>

    Examples:
      | name      | type   | isExotic |
      | "Морковь" | "Овощ" | "false"  |
