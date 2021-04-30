Feature: Command line interface
  This feature provides a range of scenarios corresponding to the
  expected behaviour of the CLI. i.e. the parsing and evaluation of
  strings provided by the user.

  Background:
    Given I initialise the cli

  Scenario: Change the mode of the cli
    Given a mode name 'convertor'
    Then the mode of the cli is 'convertor'

  Scenario: Check the result of a correct arithmetic expression
    Given a mode name 'calculator'
    And an expression '3^3'
    Then the result of the evaluation is '27'

  Scenario: Check the result of a correct arithmetic expression with rational
    Given a mode name 'calculator'
    And an expression 'r5/2 ^ 2'
    Then the result of the evaluation is '25/4'

  Scenario: Check the result of a correct arithmetic expression with spaces
    Given a mode name 'calculator'
    And an expression ' 3   ^    3   '
    Then the result of the evaluation is '27'

  Scenario: Check the result of a incorrect arithmetic expression
    Given a mode name 'calculator'
    And an expression 'bonjour'
    Then the evaluation fail

  Scenario: Check the result of a correct date addition
    Given a mode name 'date'
    And an expression '2020-12-11 10:10:10 + PT3H40M'
    Then the result of the evaluation is '2020-12-11T13:50:10Z'