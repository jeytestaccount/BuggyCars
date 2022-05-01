Feature: Test comment functionality of Buggy cars webpage

  Background:
    Given I am at the Buggy car Home Page
    And I register with Valid details and login successfully
    And I am at the Buggy car Home Page

  @smoke @regression
  Scenario: To verify user is able to comment in Buggy cars webpage successfully after login

    When I select a list of all registered models
    And I select a car with make "Bugatti" and model "Veyron" in overall model
    Then I should be able to add valid comment "Test Comment" and vote successfully
    And I should be able to see the correct date, author and comments in list of comments

  @regression
  Scenario: To verify user is unable to add invalid lengthy comments in comment section

    When I select a list of all registered models
    And I select a car with make "Bugatti" and model "Veyron" in overall model
    Then I should not be able to add invalid comments and vote

