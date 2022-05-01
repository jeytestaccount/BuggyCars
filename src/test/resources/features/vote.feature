Feature: Test Voting functionality of Buggy cars webpage

  @smoke @regression
  Scenario: To verify user is able to vote in Buggy cars webpage successfully after login

    Given I am at the Buggy car Home Page
    And I register with Valid details and login successfully
    And I am at the Buggy car Home Page
    When I select a popular model in Buggy car homepage
    Then I should be able to view the details of the model
    And I should be able to vote successfully
    And vote count should be updated correctly

  @regression
  Scenario: To verify user is unable to vote when they are not logged in

    Given I am at the Buggy car Home Page
    When I select a popular model in Buggy car homepage
    Then I should be able to view the details of the model
    And I should not be able to vote as i did not login