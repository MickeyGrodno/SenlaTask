Feature: DynamicContent page test.

Scenario: Successful check for dynamic content on the page

  Given open "http://the-internet.herokuapp.com/dynamic_content"
  And received content with characters and text
  When click to link with text "click here"
  And received content with characters and text
  Then verify that content before button click is different from content after button click