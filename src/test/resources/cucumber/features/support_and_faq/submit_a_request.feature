Feature: Submit a request

  Scenario: On submitting a blank request, the user should be able to see error messages
  - the errors should only appear for compulsory fields
  - if reCaptcha is disabled for automation software

    Given user opens the Support page
    And user clicks on "Submit a request" navigation link
    When user is taken to the correct requests page
    And user chooses "Report A Bug" issue and clicks submit for empty form
    Then user should be able to see error messages for compulsory fields