Feature: Operations on rules

  Background:
    Given the API server is running
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint

  Scenario: add a badge rule to an application
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the rule payload