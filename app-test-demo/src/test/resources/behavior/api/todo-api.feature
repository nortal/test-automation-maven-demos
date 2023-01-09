Feature: Demo Test - Todo API

  This test is for demonstration purposes.
  Testing Todo application running in Docker container.
  JDBC module is used to set initial database data.
  Feign client makes calls to the API.

  Scenario: User can add a todo via API
    When user adds a todo
    Then user should get response with added todo

  Scenario: User can get todo list via API
    Given user has todos
    When user requests todo list
    Then user should get response with a list of todos

  Scenario: User can get todo details via API
    Given user has todos
    When user requests todo details
    Then user should get response with todo details

  Scenario: User can delete a todo via API
    Given user has todos
    When user deletes a todo
    Then user should get success response
