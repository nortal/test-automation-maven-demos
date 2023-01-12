Feature: Demo Test - Todo UI

  Scenario: User can add a todo via UI
    Given user has opened Todo application in browser
    When user clicks on Add New button
    Then user should see Add New Todo page
    When user enters todo information
    Then user should see that todo is added

  Scenario: User can delete a todo via UI
    Given user has todos
    And user has opened Todo application in browser
    When user clicks on Remove button
    Then user should see that todo is deleted
