Feature: The post has a title and has many comments.

  Scenario: Get all the posts
    Given Initiate the contact with the API
    When I call GET request
    Then The following responses are returned
    | id | title |
    | 1  | user11|
    | 2  | user22|
    | 3  | user33|