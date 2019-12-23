package fr.lma.advent.day7;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.iterators.PermutationIterator;
import org.apache.commons.lang3.ArrayUtils;

import fr.lma.util.Utils;

public class Advent_7 {

	public static int currentAmpValue = 0;
	public static int maxAmpValue = 0;
	public static int currentPhase = 0;
	private static boolean toggle = false;

	public static void main(final String... args) {
		// Part 1
		final Iterator<List<Integer>> phasePermutations = new PermutationIterator<>(
				Arrays.asList(0, 1, 2, 3, 4));
		while (phasePermutations.hasNext()) {
			final List<Integer> phases = phasePermutations.next();
			System.out.println("-------------" + phases);
			int ampIndex = 1;
			for (final Integer phase : phases) {
				currentPhase = phase;
				final int oldAmpValue = currentAmpValue;
				currentAmpValue = compute(getInput("\\advent7\\day7_input_louisperso.txt"));
				System.out.println(
						String.format(">AMP%1$s (phase %2$s | input %3$s | output %4$s)",
								ampIndex,
								currentPhase,
								oldAmpValue,
								currentAmpValue));
				ampIndex++;
			}
			if (currentAmpValue > maxAmpValue) {
				maxAmpValue = currentAmpValue;
			}
			currentAmpValue = 0;
			ampIndex = 1;
		}
		System.out.println("The maximum is : " + maxAmpValue);
	}

	private static Integer compute(final Integer[] intInput) {
		Operation currentOperation;
		Integer finalOutput = null;
		try {
			for (int index = 0; index < intInput.length;) {
				currentOperation = new Operation(index, intInput);
				final OperationResult result = currentOperation.compute();
				if (result.isIndex()) {
					index = result.getOutput();
				} else {
					finalOutput = result.getOutput();
					index += currentOperation.getSize();
				}
			}
		} catch (final UnsupportedOperationException uoe) {
			// System.out.println(uoe.getMessage());
		}
		return finalOutput;
	}

	private static Integer[] getInput(final String fileName, final Integer noun, final Integer verb) {
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
		return ArrayUtils.clone(intInput);
	}

	private static Integer[] getInput(final String fileName) {
		return getInput(fileName, null, null);
	}

	public static int getOperationInput() {
		final int returnValue = toggle ? currentAmpValue : currentPhase;
		toggle = !toggle;
		return returnValue;
	}

}
