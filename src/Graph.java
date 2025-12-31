import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A Graph implementation that manages Town vertices and Road edges. Supports
 * adding/removing towns and roads, querying edges/vertices, and computing
 * shortest paths using Dijkstra's algorithm.
 * 
 * @author Ryan Jones
 * @version 5/10/25
 */
public class Graph implements GraphInterface<Town, Road> {
	private Map<Town, ArrayList<Town>> adjacent;
	private ArrayList<Town> alltown;
	private ArrayList<Road> allroads;
	private int roadCount = 0;
	private Map<Town, Integer> distances;
	private Map<Town, Town> previous;
	Set<Town> visited;

	/**
	 * Constructs an empty Graph.
	 */
	public Graph() {
		adjacent = new HashMap<>();
		alltown = new ArrayList<>();
		allroads = new ArrayList<>();
	}

	/**
	 * Gets the edge (Road) between two towns.
	 * 
	 * @param sourceVertex      the starting Town
	 * @param destinationVertex the destination Town
	 * @return the Road object if it exists, otherwise null
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Road road = new Road(sourceVertex, destinationVertex, "getEdge");
		Road target = null;
		for (Road r : allroads) {
			if (r.equals(road)) {
				target = r;
			}
		}
		return target;
	}

	/**
	 * Adds a Road between two towns.
	 * 
	 * @param sourceVertex the starting Town
	 * @param destinationVertex the destination Town
	 * @param weight the weight (distance) of the road
	 * @param description the name/description of the road
	 * @return the Road added
	 * @throws NullPointerException if any parameter is null
	 * @throws IllegalArgumentException if either town doesn't exist
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException("One of the arguments is null");
		}
		boolean source = false;
		boolean dest = false;
		for (Town t : alltown) {
			if (t.getName().equals(sourceVertex.getName()))
				source = true;
			if (t.getName().equals(destinationVertex.getName()))
				dest = true;
		}
		if (!source || !dest) {
			throw new IllegalArgumentException("The source or destination does not exist");
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		allroads.add(road);
		roadCount += 2;
		return road;
	}

	/**
	 * Adds a new town (vertex) to the graph.
	 * 
	 * @param v the Town to add
	 * @return true if added, false if it already exists
	 * @throws NullPointerException if the town is null
	 */
	@Override
	public boolean addVertex(Town v) {
		if (v == null) {
			throw new NullPointerException("The argument is null");
		}
		for (Town t : alltown) {
			if (t.getName().equals(v.getName())) {
				return false;
			}
		}
		adjacent.put(v, new ArrayList<>());
		alltown.add(v);
		return true;
	}

	/**
	 * Checks if an edge exists between two towns.
	 * 
	 * @param sourceVertex one end of the edge
	 * @param destinationVertex the other end of the edge
	 * @return true if such an edge exists, false otherwise
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Road road = new Road(sourceVertex, destinationVertex, "containsEdge");
		for (Road r : allroads) {
			if (r.equals(road)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a town exists in the graph.
	 * 
	 * @param v the Town to check
	 * @return true if it exists
	 */
	@Override
	public boolean containsVertex(Town v) {
		return alltown.contains(v);
	}

	/**
	 * Returns a set of all roads (edges) in the graph.
	 * 
	 * @return a set of Road objects
	 */
	@Override
	public Set<Road> edgeSet() {
		return new HashSet<>(allroads);
	}

	/**
	 * Returns the list of all towns in the graph.
	 * 
	 * @return list of Town objects
	 */
	public List<Town> getAlltown() {
		return alltown;
	}

	/**
	 * Returns the set of edges connected to a town.
	 * 
	 * @param vertex the Town to check
	 * @return set of Road objects
	 * @throws NullPointerException if vertex is null
	 * @throws IllegalArgumentException if vertex not in graph
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null) {
			throw new NullPointerException("The argument is null");
		}
		if (!containsVertex(vertex)) {
			throw new IllegalArgumentException("Vertex was not found");
		}
		Set<Road> town = new HashSet<>();
		for (Road r : allroads) {
			if (r.contains(vertex)) {
				town.add(r);
			}
		}
		return town;
	}

	/**
	 * Removes an edge from the graph.
	 * 
	 * @param sourceVertex start town
	 * @param destinationVertex end town
	 * @param weight weight of the road
	 * @param description road name
	 * @return removed Road or null if not found
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		if (!containsEdge(sourceVertex, destinationVertex)) {
			return null;
		}
		for (Road r : allroads) {
			if (r.equals(road)) {
				allroads.remove(r);
				return r;
			}
		}
		return null;
	}

	/**
	 * Removes a town (vertex) from the graph.
	 * 
	 * @param v the Town to remove
	 * @return true if removed, false if not present
	 */
	@Override
	public boolean removeVertex(Town v) {
		if (!alltown.contains(v)) {
			return false;
		}
		for (ArrayList<Town> neighbors : adjacent.values()) {
			neighbors.remove(v);
		}
		adjacent.remove(v);
		alltown.remove(v);
		return true;
	}

	/**
	 * Gets the instance of a town that exists in the graph matching the input.
	 * 
	 * @param v the Town to search for
	 * @return the Town in the graph, or null if not found
	 */
	public Town getVertex(Town v) {
		for (Town t : alltown) {
			if (v.equals(t)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Returns the set of all vertices (towns) in the graph.
	 * 
	 * @return a Set of Town objects
	 */
	@Override
	public Set<Town> vertexSet() {
		return adjacent.keySet();
	}
	

	public List<Road> getAllroads() {
		return allroads;
	}

	/**
	 * Computes the shortest path from source to destination using Dijkstra.
	 * 
	 * @param sourceVertex start town
	 * @param destinationVertex end town
	 * @return an ArrayList of Strings representing the path
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> result = new ArrayList<>();
		if (!previous.containsKey(destinationVertex) || distances.get(destinationVertex) == Integer.MAX_VALUE) {
			return result;
		}
		LinkedList<Town> path = new LinkedList<>();
		Town current = destinationVertex;
		while (current != null) {
			path.addFirst(current);
			current = previous.get(current);
		}
		for (int i = 0; i < path.size() - 1; i++) {
			Town from = path.get(i);
			Town to = path.get(i + 1);
			Road road = getEdge(from, to);
			if (road != null) {
				result.add(from.getName() + " via " + road.getName() + " to " + to.getName() + " " + road.getWeight()+" mi");
			}
		}
		return result;
	}

	/**
	 * Computes Dijkstra's algorithm from a source Town. Populates the distances and
	 * previous maps for path reconstruction.
	 * 
	 * @param sourceVertex the source Town
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		distances = new HashMap<>();
		previous = new HashMap<>();
		visited = new HashSet<>();

		for (Town t : alltown) {
			distances.put(t, Integer.MAX_VALUE);
			previous.put(t, null);
		}
		distances.put(sourceVertex, 0);

		TownDistanceComparator comparator = new TownDistanceComparator(distances);
		PriorityQueue<Town> pq = new PriorityQueue<>(comparator);
		pq.add(sourceVertex);

		while (!pq.isEmpty()) {
			Town current = pq.poll();
			visited.add(current);
			Set<Road> edges = edgesOf(current);

			for (Road r : edges) {
				Town neighbor = r.getSource().equals(current) ? r.getDestination() : r.getSource();
				if (visited.contains(neighbor))
					continue;
				int newDist = distances.get(current) + r.getWeight();
				if (newDist < distances.get(neighbor)) {
					distances.put(neighbor, newDist);
					previous.put(neighbor, current);
					pq.add(neighbor);
				}
			}
		}
	}

	/**
	 * Comparator for comparing towns based on distance used in Dijkstra's
	 * algorithm.
	 */
	public class TownDistanceComparator implements Comparator<Town> {
		private Map<Town, Integer> distances;

		/**
		 * Constructs a comparator using the given distances map.
		 * 
		 * @param distances a map of towns to their distances
		 */
		public TownDistanceComparator(Map<Town, Integer> distances) {
			this.distances = distances;
		}

		/**
		 * Compares two towns based on their distance values.
		 * 
		 * @param t1 the first town
		 * @param t2 the second town
		 * @return negative if t1 < t2, positive if t1 > t2, 0 if equal
		 */
		public int compare(Town t1, Town t2) {
			int d1 = distances.get(t1);
			int d2 = distances.get(t2);
			return Integer.compare(d1, d2);
		}
	}
}
