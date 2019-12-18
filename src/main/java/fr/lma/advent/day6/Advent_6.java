package fr.lma.advent.day6;

import fr.lma.util.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Advent_6 {

    public static void main(final String... args) throws Exception {
        final Set<Orbiter> tempOrbiters = Utils.getStringsInput("advent6/input_louistech.txt")
                .map(Orbiter::new)
                .collect(Collectors.toSet());
        final Set<Orbiter> allOrbiters = new HashSet<>(tempOrbiters);
        final Orbiter origin = findOrigin(allOrbiters);
        tempOrbiters.remove(origin);
        buildMap(origin, tempOrbiters);
        System.out.println("Total of orbits : " + calculateOrbitSum(allOrbiters));
        System.out.println("Number of jumps to Santa's orbit : " + getTotalNumberOfJumps(allOrbiters, "YOU", "SAN"));
    }

    public static Integer getTotalNumberOfJumps(final Set<Orbiter> orbiters, final String from, final String to) {
        final Orbiter fromOrbit = findOrbiterByName(orbiters, from).getCenter();
        final Orbiter toOrbit = findOrbiterByName(orbiters, to).getCenter();
        final Orbiter commonCenter = findCommonCenter(fromOrbit, toOrbit);
        return getNumberOfJumps(fromOrbit, commonCenter) + getNumberOfJumps(toOrbit, commonCenter);
    }

    private static Integer getNumberOfJumps(final Orbiter from, final Orbiter to) {
        Integer jumps = 0;
        Orbiter currentOrbiter = from;
        while (null != currentOrbiter && !currentOrbiter.equals(to)) {
            jumps++;
            if (currentOrbiter.getName().equals("COM")) {
                throw new IllegalStateException("Reached COM before destination !");
            }
            currentOrbiter = currentOrbiter.getCenter();
        }
        return jumps;
    }

    private static Orbiter findCommonCenter(final Orbiter orb1, final Orbiter orb2) {
        final List<Orbiter> orb2CenterList = getCenterList(orb2);
        Orbiter currentOrbiter = orb1;
        Orbiter commonCenter = null;
        while (null == commonCenter) {
            if (orb2CenterList.contains(currentOrbiter)) {
                commonCenter = currentOrbiter;
            }
            currentOrbiter = currentOrbiter.getCenter();
        }
        return commonCenter;
    }

    private static List<Orbiter> getCenterList(final Orbiter from) {
        final List<Orbiter> centerList = new ArrayList<>();
        Orbiter temp = from;
        while (temp != null && temp.getCenter() != null) {
            centerList.add(temp);
            temp = temp.getCenter();
        }
        return centerList;
    }

    public static Orbiter findOrbiterByName(final Set<Orbiter> orbiters, final String name) {
        return orbiters.stream().filter(orb -> orb.getName().equals(name)).findFirst().get();
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
