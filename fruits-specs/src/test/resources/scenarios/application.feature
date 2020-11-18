Feature: Operations on applications

  Background:
    Given the API server is running

  Scenario: get the list of applications
    When I send a GET to the /applications endpoint