# encoding: utf-8
Feature: Add goods in the FOOD database by JDBC (Cucumber)

  Background:
    Given the database connection is established

  @test-by-jdbc
  Scenario Outline: Add a new good to the FOOD table
    * I retrieve the number of rows in the FOOD table before adding a good
    * I add a new good with name <name>, type <type> and isExotic value <isExotic> to the FOOD table
    * the number of rows in the FOOD table should increase by 1
    * the last entry in the FOOD table should have values <name>, <type> and <isExotic>

    Examples:
      | name      | type   | isExotic |
      | "Морковь" | "Овощ" | "false"  |

