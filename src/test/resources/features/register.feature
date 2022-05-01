Feature: Test Register functionality of Buggy cars webpage


  @smoke @regression
  Scenario: To verify user is able to register in Buggy cars webpage successfully with valid details

    Given I am at the Buggy car Home Page
    When I register with valid user details
    Then I should be able to register successfully

  @regression
  Scenario: To verify user is unable to register in Buggy cars webpage with Invalid details

    Given I am at the Buggy car Home Page
    When I register with invalid user details
    Then I should not be able to register successfully
    And I should get an invalid parameter error message