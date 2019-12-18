package cucumber;

import fr.lma.advent.day6.Advent_6;
import fr.lma.advent.day6.Orbiter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

public class Advent_6_StepDefs {

    private Orbiter com;
    private final Set<Orbiter> orbiters = new HashSet<>();

    @Given("{string} orbits COM")
    public void orbits_com(final String name) {
        com = new Orbiter("COM", null);
        final Orbiter orbiter = new Orbiter(name, "COM");
        com.getSatellites().add(orbiter);
        orbiter.setCenter(com);
        orbiters.add(com);
        orbiters.add(orbiter);
    }

    ;

    @Given("{string} orbits {string}")
    public void orbits(final String name, final String centerName) {
        final Orbiter center = findOrbiter(centerName);
        if (null == center) {
            throw new NullPointerException();
        } else {
            final Orbiter newOrbiter = new Orbiter(name, centerName);
            newOrbiter.setCenter(center);
            center.getSatellites().add(newOrbiter);
            orbiters.add(newOrbiter);
        }
    }

    ;

    @Then("the total of orbits is {int}")
    public void the_total_of_orbits_is(final Integer expectedSize) {
        Assert.assertEquals(expectedSize.intValue(), Advent_6.calculateOrbitSum(orbiters));
    }

    ;

    private Orbiter findOrbiter(final String name) {
        Orbiter result = null;
        for (final Orbiter orbiter : orbiters) {
            if (orbiter.getName().equals(name)) {
                result = orbiter;
                break;
            }
        }
        return result;
    }

    @Then("the orbiter {string} can be found")
    public void theOrbiterCanBeFound(final String name) {
        Assert.assertNotNull(Advent_6.findOrbiterByName(orbiters, name));
    }

    @Then("the total of jumps is {int}")
    public void theTotalOfJumpsIs(final Integer expected) {
        Assert.assertEquals(expected, Advent_6.getTotalNumberOfJumps(orbiters, "YOU", "SAN"));
    }
}
