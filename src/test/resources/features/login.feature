Feature: Test Login functionality of Buggy cars webpage

  @smoke @regression
  Scenario: To verify user is able to login Buggy cars webpage successfully with Valid User ID and Password

    Given I am at the Buggy car Home Page
    When I login with valid User ID and Password
    Then I should be able to login successfully

  @regression
  Scenario: To verify user is unable to login Buggy cars webpage with Invalid User ID and Password

    Given I am at the Buggy car Home Page
    When I login with invalid User ID and Password
    Then I should not be able to login
    And I should get an error message