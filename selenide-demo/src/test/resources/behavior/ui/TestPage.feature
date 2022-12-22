Feature: Linking content together is supported

  # Example test for https://testpages.herokuapp.com
  @Gui
  Scenario: Test Page For Automating supports navigation with hyperlinks
    Given Test Pages For Automating is open in browser
    When User clicks on Basic Web Page Example link
    Then Several paragraphs are visible
     | A paragraph of text       |
     | Another paragraph of text |

    # GUI User Interactions throws errors in browser console,
    # therefore its a good example of browser console logging
  @Gui
  Scenario: Framework supports Browser console errors logging
    Given Test Pages For Automating is open in browser
    When User clicks on GUI User Interactions link
    Then Several paragraphs are visible
    |Drag me  |
    |Drop here|