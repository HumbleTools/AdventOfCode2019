import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.runner.RunWith;

import fr.lma.advent.day6.Advent_6;
import fr.lma.advent.day6.Orbiter;
import io.cucumber.java8.En;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = true)
public class RunCucumberTests implements En {

	private Orbiter com;
	private final Set<Orbiter> orbiters = new HashSet<>();

	public void chain_size_steps() {

		Given("{string} orbits COM", (final String name) -> {
			com = new Orbiter("COM", null);
			final Orbiter orbiter = new Orbiter(name, "COM");
			com.getSatellites().add(orbiter);
			orbiter.setCenter(com);
			orbiters.add(com);
			orbiters.add(orbiter);
		});

		Given("{string} orbits {string}", (final String name, final String centerName) -> {
			final Orbiter orbiter = findOrbiter(centerName);
			if (null == orbiter) {
				throw new NullPointerException();
			} else {
				final Orbiter newOrbiter = new Orbiter(name, centerName);
				newOrbiter.setCenter(orbiter);
				orbiter.getSatellites().add(newOrbiter);
			}
		});

		Then("the total of orbits is {int}", (final Integer expectedSize) -> {
			Assert.assertEquals(Advent_6.calculateOrbitSum(orbiters), expectedSize.intValue());
		});
	}

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

}
