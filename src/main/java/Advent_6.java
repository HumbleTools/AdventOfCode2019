import com.lma.util.Utils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Advent_6 {

    public static void main(final String... args) throws Exception {
        final Set<Orbiter> tempOrbiters = Utils.getStringsInput("advent6/input_louisperso.txt")
                .filter(str -> !str.startsWith("###"))
                .map(Orbiter::new)
                .collect(Collectors.toSet());
        final Set<Orbiter> allOrbiters = new HashSet<>(tempOrbiters);
        final Orbiter origin = findOrigin(allOrbiters);
        tempOrbiters.remove(origin);
        buildMap(origin, tempOrbiters);
        System.out.println(calculateOrbitSum(allOrbiters));
    }

    private static int calculateOrbitSum(final Set<Orbiter> orbiters) {
        return orbiters.stream()
                .map(Advent_6::chainSizeFrom)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static Integer chainSizeFrom(final Orbiter orbiter) {
        int size = 1;
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
        return orbiters.stream().filter(o -> o.getCenterName().equals("COM")).findFirst().get();
    }

}

class Orbiter {

    private Orbiter center;
    private final String centerName;
    private final String name;
    private final List<Orbiter> satellites;

    public Orbiter(final String value) {
        final String[] splitted = value.split("\\)");
        centerName = splitted[0];
        name = splitted[1];
        satellites = new ArrayList<>();
    }

    /**
     * @return the center
     */
    public Orbiter getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(final Orbiter center) {
        this.center = center;
    }

    /**
     * @return the satellites
     */
    public List<Orbiter> getSatellites() {
        return satellites;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the centerName
     */
    public String getCenterName() {
        return centerName;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31)
                .append(centerName)
                .append(name)
                .toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Orbiter)) {
            return false;
        }
        final Orbiter other = (Orbiter) obj;
        return new EqualsBuilder()
                .append(centerName, other.centerName)
                .append(name, other.name)
                .isEquals();
    }

}
