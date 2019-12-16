package fr.lma.advent.day6;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Orbiter {

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

	public Orbiter(final String name, final String centerName) {
		this.name = name;
		this.centerName = centerName;
		satellites = new ArrayList<>();
	}

	/**
	 * @return the center
	 */
	public Orbiter getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
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
