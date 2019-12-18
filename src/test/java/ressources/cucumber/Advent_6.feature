Feature: advent of code day 6

  Scenario: Orbit sum calculation
    Given "AA" orbits COM
    And "BB" orbits "AA"
    And "CC" orbits "BB"
    And "EE" orbits "BB"
    And "FF" orbits "EE"
    Then the total of orbits is 13

  Scenario: Orbit search
    Given "AA" orbits COM
    And "BB" orbits "AA"
    And "CC" orbits "BB"
    And "EE" orbits "BB"
    And "FF" orbits "EE"
    Then the orbiter "CC" can be found

  Scenario: Orbit sum calculation
    Given "B" orbits COM
    And "C" orbits "B"
    And "D" orbits "C"
    And "E" orbits "D"
    And "F" orbits "E"
    And "G" orbits "B"
    And "H" orbits "G"
    And "I" orbits "D"
    And "J" orbits "E"
    And "K" orbits "J"
    And "L" orbits "K"
    Then the total of orbits is 42

  Scenario: Jump total calculation
    Given "B" orbits COM
    And "C" orbits "B"
    And "D" orbits "C"
    And "E" orbits "D"
    And "F" orbits "E"
    And "G" orbits "B"
    And "H" orbits "G"
    And "I" orbits "D"
    And "SAN" orbits "I"
    And "J" orbits "E"
    And "K" orbits "J"
    And "L" orbits "K"
    And "YOU" orbits "K"
    Then the total of jumps is 4
