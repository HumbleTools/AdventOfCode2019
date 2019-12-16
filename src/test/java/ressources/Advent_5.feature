Feature: advent of code day 5

	Scenario: Orbit sum calculation
		Given "AA" orbits COM
		And "BB" orbits "AA"
		And "CC" orbits "BB"
		And "EE" orbits "BB"
		And "FF" orbits "EE"
		Then the total of orbits is 13