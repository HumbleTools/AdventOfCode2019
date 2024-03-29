package fr.lma.advent;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Advent_1 {

    private static final String INPUT = "85824,112173,142065,55390,111295,148584,123987,66433,95844,"
            + "122580,146901,107700,63930,100389,139126,122243,65950,87443,137945,"
            + "147755,86370,66749,133758,68317,147417,97202,75113,105996,103130,113328,"
            + "128427,108580,131832,147958,137067,117676,61678,127254,51090,69924,58966,"
            + "127437,144987,80181,85474,100216,119810,129946,84880,61614,107350,77076,"
            + "93028,140464,86826,67901,118846,118658,63646,63328,106271,87376,90156,143507,"
            + "139729,140393,70324,77304,81383,127336,144535,93496,145119,73128,103189,69519,"
            + "95701,112919,104766,124188,69855,99495,147075,115498,115468,68706,51445,69871,"
            + "134449,130838,105809,110721,50893,126521,81542,81384,148523,105748,93331,129279";

    private static int part1Total = 0;

    public static void main(final String[] args) {
        int totalFuel = Arrays.stream(INPUT.split(","))
                .map(value -> Integer.valueOf(value))
                .map(value -> {
                    int tempFuel = calculateFuel(value);
                    part1Total += tempFuel;
                    int total = 0;
                    while(tempFuel>0){
                        total += tempFuel;
                        tempFuel = calculateFuel(tempFuel);
                    }
                    return total;
                })
                .collect(Collectors.summingInt(value -> value));
        System.out.println(part1Total); // Expecting 3453056 (part 1)
        System.out.println(totalFuel); // Expecting 5176705 (part 2)
    }

    private static int calculateFuel(final int mass){
        return ((mass-(mass % 3))/3)-2;
    }
}