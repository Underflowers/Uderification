Feature: Trigger events

  Background:
    Given the API server is running
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a point scale payload named "test_point_scale_1"
    When I POST the point scale payload to the /pointScales endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint

  Scenario: i POST an event to have badge reward
    Given I have an event payload of user "test_user"
    When I POST the event payload
    Then I receive a 201 status code

  Scenario: i POST an event to have point scale reward
    Given I have an event payload of user "test_user"
    When I POST the event payload
    Then I receive a 201 status code

  Scenario: i POST an event to have point scale and badge reward and see my rewards
    Given I have a badge-and-scale rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have an event payload of user "test_user"
    When I POST the event payload
    Then I receive a 201 status code

    When I send a GET to the users scores endpoint of user "test_user"
    Then I receive a 200 status code
    And I see I have a point

    When I send a GET to the user badges endpoint of user "test_user"
    Then I receive a 200 status code
    And I see I have a badge

  Scenario: I test the leaderboard
    Given I have a point scale payload named "test_point_scale_leaderboard"
    Then I POST the point scale payload to the /pointScales endpoint

    Given I have a scale rule payload
    Then I POST the rule payload to the /rules endpoint

    Given I have an event payload of user "test_user1"
    When I POST the event payload
    Then I receive a 201 status code
    Given I have an event payload of user "test_user2"
    When I POST the event payload
    Then I receive a 201 status code
    When I POST the event payload
    Then I receive a 201 status code
    Then User "test_user2" is first in "test_point_scale_leaderboard" leaderboard