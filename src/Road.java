import java.util.Objects;

/**
 * Represents a Road connecting two Towns with a specified name and weight (distance).
 * A Road is considered equal to another if it connects the same two towns, regardless of direction.
 * 
 * @author Ryan Jones
 * @version 5/5/25
 */
public class Road implements Comparable<Road> {
	private Town source;
	private Town destination;
	private int weight;
	private String name;

	/**
	 * Constructs a Road with a specified source, destination, weight (distance), and name.
	 * 
	 * @param source the starting Town
	 * @param destination the ending Town
	 * @param degrees the weight or distance of the road
	 * @param name the name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
	}

	/**
	 * Constructs a Road with a specified source, destination, and name.
	 * Default weight is set to 1.
	 * 
	 * @param source the starting Town
	 * @param destination the ending Town
	 * @param name the name of the road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
		this.name = name;
	}

	/**
	 * Gets the source town of the road.
	 * 
	 * @return the source Town
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * Gets the destination town of the road.
	 * 
	 * @return the destination Town
	 */
	public Town getDestination() {
		return destination;
	}

	/**
	 * Gets the weight (distance) of the road.
	 * 
	 * @return the weight of the road
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Gets the name of the road.
	 * 
	 * @return the name of the road
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks whether the specified object is equal to this road.
	 * Two roads are considered equal if they connect the same towns, regardless of direction.
	 * 
	 * @param obj the object to compare
	 * @return true if the roads are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		return (Objects.equals(destination, other.destination) && Objects.equals(source, other.source))
			|| (Objects.equals(destination, other.source) && Objects.equals(source, other.destination));
	}

	/**
	 * Returns a string representation of the road.
	 * 
	 * @return a formatted String describing the road
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Compares two roads based on their names.
	 * 
	 * @param o the other Road to compare to
	 * @return a negative integer, zero, or a positive integer as this name is less than, equal to, or greater than the other
	 */
	@Override
	public int compareTo(Road o) {
		return name.compareTo(o.name);
	}

	/**
	 * Checks whether this road connects to a given town.
	 * 
	 * @param town the Town to check
	 * @return true if the road connects to the town, false otherwise
	 */
	public boolean contains(Town town) {
		return source.equals(town) || destination.equals(town);
	}
}
