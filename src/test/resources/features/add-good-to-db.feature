Feature: Add and manage goods in the FOOD database

  Background:
    Given the database connection is established

  @test-by-jdbc
  Scenario: Add a new good to the FOOD table
    When I retrieve the number of rows in the FOOD table before adding a good
    And I add a new good to the FOOD table
    Then the number of rows in the FOOD table should increase by 1
    And the last entry in the FOOD table should match the added good
    Then I remove the last added good from the FOOD table
    Then the number of rows in the FOOD table should decrease by 1
    And the last added good should not exist in the FOOD table
