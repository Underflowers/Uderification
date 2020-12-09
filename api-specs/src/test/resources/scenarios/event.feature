Feature: Trigger events

  Background:
    Given the API server is running
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a point scale payload
    When I POST the point scale payload to the /pointScales endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint

  Scenario: i POST an event to have badge reward
    Given I have an event payload
    When I POST the event payload
    Then I receive a 201 status code

  Scenario: i POST an event to have point scale reward
    Given I have a scale rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have an event payload
    When I POST the event payload
    Then I receive a 201 status code

  Scenario: i POST an event to have point scale and badge reward and see my rewards
    Given I have a badge-and-scale rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have an event payload
    When I POST the event payload
    Then I receive a 201 status code

    When I send a GET to the /users/id/scores endpoint
    Then I receive a 200 status code
    And I see I have a point

    When I send a GET to the /users/id/badges endpoint
    Then I receive a 200 status code
    And I see I have a badge
