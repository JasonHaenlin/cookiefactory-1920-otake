Feature: App

  Background:
    Given an App

  Scenario: greeting
    When App greeting
    Then Say "Hello world."
