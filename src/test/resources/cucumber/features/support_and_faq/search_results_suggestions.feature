Feature: Search results and article suggestions or autocomplete

  Scenario Outline: On entering search keywords, the user should be able to see article suggestions
  - based on validity of search keywords entered
  - with keywords emboldened

    Given user opens the Support page
    When user enters the <validity> keywords <keywords>
    Then user sees suggestions based on the validity of keywords
    And if one or more suggestions appear, the keywords should be emboldened
    Examples:
      | validity  | keywords                |
      | "valid"   | "setting"               |
      | "valid"   | "Getting Started"       |
      | "invalid" | "Rainbows and Unicorns" |


  Scenario: On entering search keywords, and clicking an article suggestion, the user should be taken to an article page
  - and the article on the page should match the suggestion clicked on
  - search keywords must be valid

    Given user opens the Support page
    And user enters the "valid" keywords "getting Started"
    And user sees suggestions based on the validity of keywords
    When user clicks on first article suggestion
    Then user is taken to the correct article page


  Scenario Outline: On entering search keywords and pressing enter, user should be able to see search results
  - number of search results should be displayed based on validity
  - and keywords should be highlighted

    Given user opens the Support page
    When user enters the <validity> keywords <keywords>
    And user presses enter
    Then user is taken to the search results page
    And if one or more results appear, highlighted keywords are displayed
    Examples:
      | validity  | keywords                |
      | "valid"   | "setting"               |
      | "valid"   | "Getting Started"       |
      | "invalid" | "Rainbows and Unicorns" |