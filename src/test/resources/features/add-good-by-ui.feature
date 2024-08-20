# encoding: utf-8
Feature: Add goods to the FOOD table via UI

  Background:
    Given I am on the Home page

  @test-by-UI
  Scenario Outline: Add an exotic fruit to the FOOD table
    When I navigate to the Goods page
    And I click the button "Добавить"
    And I see the modal window for adding a good
    And I fill in the name with <name>
    And I select the type <type>
    And I check the checkbox "Экзотический"
    And I save the new good
    Then the new good should be added to the FOOD table
    And the last row should contain <name>, <type>, <isExotic>

    Examples:
      | name     | type    | isExotic |
      | "Ананас" | "Фрукт" | "true"   |


  @test-by-UI
  Scenario Outline: Add a non-exotic vegetable to the FOOD table
    When I navigate to the Goods page
    And I click the button "Добавить"
    And I see the modal window for adding a good
    And I fill in the name with <name>
    And I select the type <type>
    And I do not check the checkbox "Экзотический"
    And I save the new good
    Then the new good should be added to the FOOD table
    And the last row should contain <name>, <type>, <isExotic>

    Examples:
      | name      | type   | isExotic |
      | "Морковь" | "Овощ" | "false"  |
