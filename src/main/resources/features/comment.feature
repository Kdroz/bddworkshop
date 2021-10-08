Feature: The comments are made by a user

  Scenario Outline: Get all the comment
    Given Initiate the contact with the API
    When Call the GET comment function with "<id>"
    Then The following responses is expected for "<body>" and "<postId>"
    Examples: All the comments
      | id | body                            | postId|
      | 1  | comments of the year(user11)    | 1     |
      | 2  | my comment is better(user22)    | 1     |
      | 3  | mine is better than all (user33) | 1     |
