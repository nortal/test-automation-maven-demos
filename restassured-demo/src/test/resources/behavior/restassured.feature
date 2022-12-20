Feature: Rest Assured Demo Test - Cat Fact API

  Scenario: User gets fact about cats
    When user requests cat fact
    Then user should get response with cat fact

  Scenario: User gets list of facts about cats
    When user requests a list of cat facts
    Then user should get response with a list of cat facts

  Scenario: User gets list of cat breeds
    When user requests a list of cat breeds
    Then user should get response with a list of cat breeds
