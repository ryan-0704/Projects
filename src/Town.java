import java.util.Objects;

/**
 * Represents a town with a unique name.
 * Implements Comparable to allow sorting by name.
 * 
 * @author Ryan Jones
 * @version 5/5/25
 */
public class Town implements Comparable<Town> {
	private String name;

	/**
	 * Constructs a new Town with the given name.
	 * 
	 * @param name the name of the town
	 */
	public Town(String name) {
		this.name = name;
	}

	/**
	 * Copy constructor that creates a new Town with the same name as the given town.
	 * 
	 * @param town the Town to copy
	 */
	public Town(Town town) {
		this.name = town.name;
	}

	/**
	 * Returns the name of this town.
	 * 
	 * @return the town name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Computes the hash code of this town based on its name.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * Checks whether this town is equal to another object.
	 * Two towns are considered equal if they have the same name.
	 * 
	 * @param obj the object to compare to
	 * @return true if the towns have the same name, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Town other = (Town) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * Returns a string representation of the town (its name).
	 * 
	 * @return the name of the town
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Compares this town with another town alphabetically by name.
	 * 
	 * @param o the other Town to compare to
	 * @return a negative integer, zero, or a positive integer as this name
	 *         is less than, equal to, or greater than the specified town's name
	 */
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.name);
	}
}
