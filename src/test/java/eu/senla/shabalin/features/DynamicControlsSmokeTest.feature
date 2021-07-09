Feature: DynamicControls page test.

  Scenario: After clicking the Remove button, the checkbox disappears

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When press button with text "Remove"
    Then the checkbox will be removed

  Scenario: After clicking the Remove button and click Add button the checkbox appears

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When press button with text "Remove"
    And press button with text "Add"
    Then the checkbox will be added

  Scenario: When click on the checkbox, it changes its state to "selected"

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When click checkbox
    Then checkbox changes state to "selected"

  Scenario: When twice click on the checkbox, it changes its state to "not selected"

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When click checkbox
    And click checkbox
    Then checkbox changes state to "not selected"

  Scenario: When click on the "Enable" button, the text field becomes enabled

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When press button with text "Enable"
    Then text field changes state to "enabled"

  Scenario: When click on the "Enable" button and click "Disable" button, the text field becomes disabled

    Given open "http://the-internet.herokuapp.com/dynamic_controls"
    When press button with text "Enable"
    And press button with text "Disable"
    Then text field changes state to "disabled"