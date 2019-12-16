Feature: advent of code day 6

	Scenario: Orbit sum calculation
		Given "AA" orbits COM
		And "BB" orbits "AA"
		And "CC" orbits "BB"
		And "EE" orbits "BB"
		And "FF" orbits "EE"
		Then the total of orbits is 13
		
	Scenario: Orbir sum calculation 42
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