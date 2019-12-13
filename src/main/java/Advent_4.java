import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Advent_4 {

    private final static String INPUT = "372037-905157";
    // Part 1 : 481.
    // Part 2 : 299.

    public static void main(final String[] args) {
        final Set<String> passwords = findPasswords(parseRange(INPUT));
        System.out.println(String.format("%1s passwords were found : %1s", passwords.size(), passwords));
    }

    private static Set<String> findPasswords(final int[] input) {
        return IntStream.rangeClosed(input[0], input[1])
                .filter(number -> twoAdjacentOkButNotMoreAlone(number))
                .filter(number -> hasIncreasingDigits(number))
                .boxed()
                .map(number -> String.valueOf(number))
                .collect(Collectors.toSet());
    }

    public static boolean hasIncreasingDigits(final int number) {
        final char[] numbers = String.valueOf(number).toCharArray();
        for (int i = 1; i < numbers.length; i++) {
            if (Integer.valueOf(numbers[i]) < Integer.valueOf(numbers[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsDoubleAdjacent(final int number) {
        final char[] numbers = String.valueOf(number).toCharArray();
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == numbers[i - 1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean twoAdjacentOkButNotMoreAlone(final int number) {
        final char[] numbers = String.valueOf(number).toCharArray();
        for (int i = 3; i < numbers.length; i++) {
            char numb1 = numbers[i - 3];
            char numb2 = numbers[i - 2];
            char numb3 = numbers[i - 1];
            char numb4 = numbers[i];
            if ((numb1 != numb2 && numb2 == numb3 && numb3 != numb4)
                    || (numb1 == numb2 && numb2 != numb3 && i == 3)
                    || (numb3 == numb4 && numb2 != numb3 && i + 1 == numbers.length)) {
                return true;
            }
        }
        return false;
    }

    public static int[] parseRange(final String input) {
        final String[] str = input.split("-");
        return new int[]{ Integer.parseInt(str[0]), Integer.parseInt(str[1]) };
    }

}
