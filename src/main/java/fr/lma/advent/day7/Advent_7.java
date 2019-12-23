package fr.lma.advent.day7;

import fr.lma.util.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Arrays;
import java.util.Iterator;

public class Advent_7 {

    public static void main(final String... args) {
        // Part 1
        final Iterator<int[]> phaseCombinations = CombinatoricsUtils.combinationsIterator(5, 5);
        while (phaseCombinations.hasNext()) {
            final int[] phases = phaseCombinations.next();
            compute(ArrayUtils.clone(getInput("\\advent7\\day7_input_e1.txt")));
        }
    }

    static void compute(final Integer[] intInput) {
        Operation currentOperation;
        try {
            for (int index = 0; index < intInput.length; ) {
                currentOperation = new Operation(index, intInput);
                final OperationResult result = currentOperation.compute();
                if (null == result) {
                    continue;
                }
                if (result.isIndex()) {
                    index = result.getOutput();
                } else {
                    index += currentOperation.getSize();
                    final Integer output = result.getOutput();
                    System.out.println(output);
                }
            }
        } catch (final UnsupportedOperationException uoe) {
            System.out.println(uoe.getMessage());
        }
    }

    static Integer[] getInput(final String fileName, final Integer noun, final Integer verb) {
        final String input = Utils.getStringsInput(fileName).findFirst().get();
        final Integer[] intInput = Arrays.stream(input.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        if (null != noun) {
            intInput[1] = noun;
        }
        if (null != verb) {
            intInput[2] = verb;
        }
        return intInput;
    }

    static Integer[] getInput(final String fileName) {
        return getInput(fileName, null, null);
    }

}
