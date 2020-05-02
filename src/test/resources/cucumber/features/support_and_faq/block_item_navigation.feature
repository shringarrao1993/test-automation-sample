Feature: Block item navigation

  Scenario: On clicking a block item, the user should be taken to a categories page
  - and the welcome message should match the block item clicked on
  - and the breadcrumbs should contain the block item clicked on

    Given user opens the Support page
    When user clicks on the block item "Guests"
    Then user is taken to the correct categories page