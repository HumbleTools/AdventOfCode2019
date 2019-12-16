package fr.lma.advent.day6;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import fr.lma.util.Utils;

public class Advent_6 {

	public static void main(final String... args) throws Exception {
		final Set<Orbiter> tempOrbiters = Utils.getStringsInput("advent6/input_louisperso.txt")
				.map(Orbiter::new)
				.collect(Collectors.toSet());
		final Set<Orbiter> allOrbiters = new HashSet<>(tempOrbiters);
		final Orbiter origin = findOrigin(allOrbiters);
		tempOrbiters.remove(origin);
		buildMap(origin, tempOrbiters);
		System.out.println(calculateOrbitSum(allOrbiters));

		// TODO :
		// Add some Gherkins <3
		// code findSanta
		// code findYou(refactorFindSanta)
		// Get center of both
		// find path of both until first common ancestor
		// calculate number of jumps
		// is it required to perform the jumps ?
	}

	public static int calculateOrbitSum(final Set<Orbiter> orbiters) {
		return orbiters.stream()
				.map(Advent_6::chainSizeFrom)
				.mapToInt(Integer::intValue)
				.sum();
	}

	public static Integer chainSizeFrom(final Orbiter orbiter) {
		int size = 0;
		Orbiter currentOrbiter = orbiter;
		while (Objects.nonNull(currentOrbiter.getCenter())) {
			size++;
			currentOrbiter = currentOrbiter.getCenter();
		}
		return size;
	}

	private static void buildMap(final Orbiter center, final Set<Orbiter> orbiters) {
		center.getSatellites().addAll(orbiters.stream()
				.filter(o -> o.getCenterName().equals(center.getName()))
				.collect(Collectors.toSet()));
		orbiters.removeAll(center.getSatellites());
		for (final Orbiter orbiter : center.getSatellites()) {
			orbiter.setCenter(center);
			if (!orbiters.isEmpty()) {
				buildMap(orbiter, orbiters);
			}
		}
	}

	private static Orbiter findOrigin(final Set<Orbiter> orbiters) {
		final Orbiter origin = new Orbiter("COM", null);
		orbiters.stream()
				.filter(orb -> orb.getCenterName().equals("COM"))
				.map(orb -> {
					orb.setCenter(origin);
					return orb;
				}).close();
		return origin;
	}

}
