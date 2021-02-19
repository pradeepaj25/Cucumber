Feature: Fetch all the books published by a author

  Scenario: Fetch all the books published by a author
    Given the application is running
    When I fetch the lists of books
    Then the API should return list of books written by author
    
  Scenario: Fetch all the best-seller list for a published date
    When Published date is given
    Then the API should return all the best-seller list for a published date   
    
  Scenario: negative scenario to check if invalid published date is given
    When Invalid date is given
    Then the API should return ERROR
    
  Scenario: Fetch all the best-seller list when published date is blank
    When Published date is blank
    Then the API should return the current weeks best-sellers lists  
    
  Scenario: Fetch all the best-seller list when published date is future date
    When Published date is future date
    Then the API should return blank list  
    
    