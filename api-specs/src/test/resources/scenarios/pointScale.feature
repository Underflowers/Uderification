Feature: Operations on point scale

  Background:
    Given the API server is running
    Given I have an application payload
    When I POST the application payload to the /applications endpoint

  Scenario: add a point scale to an application
    Given I have a point scale payload named "test_point_scale"
    When I POST the point scale payload to the /pointScales endpoint
    Then I receive a 201 status code
    And I receive a payload that is the same as the pointScale payload

  Scenario: get the list of point scales
    When I send a GET to the /pointScales endpoint
    Then I receive a 200 status code