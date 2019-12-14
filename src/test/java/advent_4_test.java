import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

public class advent_4_test {

	@ParameterizedTest
	@CsvSource({ "10-20, 10, 20", "600-4567, 600, 4567" })
	public void test_ParseRange(final ArgumentsAccessor args) {
		// Act
		final int[] result = Advent_4.parseRange(args.getString(0));

		// Assert
		Assertions.assertEquals(result[0], args.getInteger(1));
		Assertions.assertEquals(result[1], args.getInteger(2));
	}

	@ParameterizedTest
	@CsvSource({ "1111, true", "123445, true", "12345, false" })
	public void test_containsDoubleAdjacent(final ArgumentsAccessor args) {
		Assertions.assertEquals(args.getBoolean(1),
				Advent_4.containsDoubleAdjacent(args.getInteger(0)));
	}

	@ParameterizedTest
	@CsvSource({ "1111, true", "77777, true", "1234556, true", "765, false" })
	public void test_hasIncreasingDigits(final ArgumentsAccessor args) {
		Assertions.assertEquals(args.getBoolean(1),
				Advent_4.hasIncreasingDigits(args.getInteger(0)));
	}

	@ParameterizedTest
	@CsvSource({
			"377778, false",
			"11111, false",
			"112233, true",
			"12344555, true",
			"123444, false",
			"111122, true",
			"11199111, true",
			"9911111, true"
	})
	public void test_twoAdjacentOkButNotWhenMoreAlone(final ArgumentsAccessor args) {
		Assertions.assertEquals(args.getBoolean(1),
				Advent_4.twoAdjacentOkButNotMoreAlone(args.getInteger(0)));
	}
}
