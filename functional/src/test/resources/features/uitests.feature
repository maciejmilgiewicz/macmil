@UITests
Feature: ui tests

  @CheckPrices
  Scenario Outline: validating asset prices are above zero on markets page
    Given i land on the home page
    When i navigate to prices page
    And i filter for asset "<asset>"
    Then all prices in the table should be more than zero

    Examples:
      | asset |
      | BTC   |
      | ETH   |
