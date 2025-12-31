import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Manages a graph of Towns and Roads using the Graph class.
 * Provides higher-level methods for interacting with the graph
 * using String inputs and file population.
 * 
 * @author Ryan Jones
 * @version 5/11/25
 */
public class TownGraphManager implements TownGraphManagerInterface {
	Graph graph = new Graph();

	/**
	 * Adds a road (edge) between two towns with the given weight and name.
	 * 
	 * @param town1 the name of the first town
	 * @param town2 the name of the second town
	 * @param weight the distance between the towns
	 * @param roadName the name of the road
	 * @return true if the road was added successfully, false otherwise
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Road r = graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
		return r != null;
	}

	/**
	 * Retrieves the road (edge) information between two towns.
	 * 
	 * @param town1 the name of the first town
	 * @param town2 the name of the second town
	 * @return a string representation of the road
	 */
	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).toString();
	}

	/**
	 * Adds a town (vertex) to the graph.
	 * 
	 * @param v the name of the town to add
	 * @return true if the town was added, false otherwise
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	/**
	 * Retrieves the Town object by name.
	 * 
	 * @param name the name of the town
	 * @return the corresponding Town object, or null if not found
	 */
	@Override
	public Town getTown(String name) {
		return graph.getVertex(new Town(name));
	}

	/**
	 * Checks whether the graph contains a town.
	 * 
	 * @param v the name of the town
	 * @return true if the town exists, false otherwise
	 */
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	/**
	 * Checks whether a road (edge) exists between two towns.
	 * 
	 * @param town1 the name of the first town
	 * @param town2 the name of the second town
	 * @return true if the road exists, false otherwise
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * Returns a list of all roads in the graph.
	 * 
	 * @return an ArrayList of strings representing all roads
	 */
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> str = new ArrayList<>();
		for (Road r : roads) {
			str.add(r.toString());
		}
		return graph.getAllroads();
	}

	/**
	 * Deletes the road between two towns if it exists.
	 * 
	 * @param town1 the name of the first town
	 * @param town2 the name of the second town
	 * @param road the name of the road
	 * @return true if the road was successfully deleted, false otherwise
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Road r = graph.removeEdge(new Town(town1), new Town(town2), 1, road);
		return r != null;
	}

	/**
	 * Deletes a town and all connected roads from the graph.
	 * 
	 * @param v the name of the town to delete
	 * @return true if the town was deleted, false otherwise
	 */
	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	/**
	 * Returns a list of all towns in the graph.
	 * 
	 * @return an ArrayList of town names
	 */
	@Override
	public ArrayList<String> allTowns() {
		List<Town> list = graph.getAlltown(); 
		ArrayList<String> town = new ArrayList<>();
		for (Town t : list) {
			town.add(t.toString());
		}
		return town;
	}

	/**
	 * Computes the shortest path between two towns using Dijkstra's algorithm.
	 * 
	 * @param town1 the starting town
	 * @param town2 the destination town
	 * @return an ArrayList of Strings representing the path
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	/**
	 * Populates the graph from a file, where each line represents a road
	 * 
	 * @param selectedFile the file containing the graph data
	 * @throws FileNotFoundException if the file does not exist
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		Scanner scan = new Scanner(selectedFile);

		while (scan.hasNext()) {
			String line = scan.nextLine();
			if (line.trim().isEmpty()) {
				continue;
			}
			String[] info = line.split("[,;]");
			String name = info[0].trim();
			int distance = Integer.parseInt(info[1].trim());
			String town1 = info[2].trim();
			String town2 = info[3].trim();

			addTown(town1);
			addTown(town2);
			addRoad(town1, town2, distance, name);
		}
	}
}
