Feature: deposit
  Deposit operation between two accounts

  Scenario: deposit in different accounts
    Given two different accounts
    When I call to the deposit service
    Then a transfer result is returned
    
  Scenario: deposit in same accounts
    Given the same accounts
    When I call to the deposit service
    Then an error is returned
    
  Scenario: deposit in different accounts without balance
    Given two different accounts and the origin without balance
    When I call to the deposit service
    Then a balance error is returned
    
  Scenario: deposit providing only one account
    Given an origin without destination
    When I call to the deposit service
    Then an account not validated is returned