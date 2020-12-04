Feature: Operations on badges

  Background:
    Given the API server is running
    Given I have an application payload
    When I POST the application payload to the /applications endpoint

  Scenario: add a badge to an application
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the badge payload

  Scenario: get the list of badges
    When I send a GET to the /badges endpoint
    Then I receive a 200 status code
    And I receive a payload that is the same as the badge payload