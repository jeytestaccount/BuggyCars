Feature: Test model details display functionality of Buggy cars webpage

  Background:
    Given I am at the Buggy car Home Page

  @smoke @regression
  Scenario Outline: To verify user is able to view the details in Models page

    Given I login with valid User ID and Password
    When I select a list of all registered models
    And I select a make "<make>"  in overall model page
    And I should be able to view model table
    Then I should be able to view the list of models by Make "<make>"

    Examples:
      | make    |
      | Bugatti |
      | Pagani  |

  @regression
  Scenario Outline: To verify user is not able to view the details of Models not belongs to Make

    Given I login with valid User ID and Password
    When I select a list of all registered models
    And I select a make "<make>"  in overall model page
    And I should be able to view model table
    Then I should not be able to view a model which is not belongs to the Make "<make>"

    Examples:
      | make        |
      | Lamborghini |
      | Lancia      |