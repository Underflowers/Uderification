Feature: Operations on rules

  Background:
    Given the API server is running
    Given I have an application payload

    When I POST the application payload to the /applications endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint

    Given I have a point scale payload named "point_point_scale"
    When I POST the point scale payload to the /pointScales endpoint

  Scenario: add a badge rule to an application
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the rule payload

  Scenario: add a scale rule to an application
    Given I have a scale rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the rule payload

  Scenario: add a badge-and-scale rule to an application
    Given I have a badge-and-scale rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the rule payload