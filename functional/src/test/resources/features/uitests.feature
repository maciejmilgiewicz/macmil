@UITests
Feature: ui tests

  @CreateAccout
  Scenario Outline: validating create account form button on sign up page
    Given i land on the home page
    When i navigate to sign up page
    And i enter email as "<email>"
    And i enter username as "<username>"
    And i enter password as "<password>"
    And i choose a country of residence
    And i accept terms
    Then create account button should be enabled

    Examples:
      | email             | username  | password  |
      | testuser@test.com | testuser  | abcdefg1@ |

  @CheckPrices
  Scenario Outline: validating asset prices are above zero on markets page
    Given i land on the home page
    When i navigate to markets page
    And i filter for asset "<asset>"
    Then all prices in the table should be more than zero

    Examples:
      | asset |
      | BTC   |
      | ETH   |