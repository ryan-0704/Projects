import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Graph_STUDENT_Test {

	private Graph graph;
	private Town townA;
	private Town townB;
	private Town townC;

	@BeforeEach
	public void setUp() {
		graph = new Graph();
		townA = new Town("A");
		townB = new Town("B");
		townC = new Town("C");
		graph.addVertex(townA);
		graph.addVertex(townB);
	}

	@Test
	public void testAddVertex() {
		assertTrue(graph.addVertex(townC));
		assertFalse(graph.addVertex(townA));
	}

	@Test
	public void testAddEdge() {
		Road r = graph.addEdge(townA, townB, 5, "A-B Road");
		assertNotNull(r);
		assertEquals("A-B Road", r.getName());
		assertEquals(5, r.getWeight());
	}

	@Test
	public void testGetEdge() {
		graph.addEdge(townA, townB, 3, "Main St");
		Road r = graph.getEdge(townA, townB);
		assertNotNull(r);
		assertEquals("Main St", r.getName());
	}

	@Test
	public void testContainsVertex() {
		assertTrue(graph.containsVertex(townA));
		assertFalse(graph.containsVertex(new Town("Z")));
	}

	@Test
	public void testContainsEdge() {
		assertFalse(graph.containsEdge(townA, townB));
		graph.addEdge(townA, townB, 4, "EdgeAB");
		assertTrue(graph.containsEdge(townA, townB));
	}

	@Test
	public void testRemoveEdge() {
		graph.addEdge(townA, townB, 2, "EdgeToRemove");
		Road removed = graph.removeEdge(townA, townB, 2, "EdgeToRemove");
		assertNotNull(removed);
		assertEquals("EdgeToRemove", removed.getName());
	}

	@Test
	public void testRemoveVertex() {
		assertTrue(graph.removeVertex(townA));
		assertFalse(graph.removeVertex(townA));
	}

	@Test
	public void testEdgeSet() {
		graph.addEdge(townA, townB, 7, "Highway");
		Set<Road> roads = graph.edgeSet();
		assertEquals(1, roads.size()); 
	}

	@Test
	public void testEdgesOf() {
	    graph.addVertex(townC);
	    graph.addEdge(townA, townB, 5, "RoadAB");
	    graph.addEdge(townA, townC, 3, "RoadAC");

	    Set<Road> edgesOfA = graph.edgesOf(townA);
	    assertEquals(2, edgesOfA.size());
	    for (Road r : edgesOfA) {
	        assertTrue(r.contains(townA));
	    }

	    try {
	        graph.edgesOf(null);
	    } catch (NullPointerException e) {
	    	
	    }

	    Town fakeTown = new Town("Ghost");
	    try {
	        graph.edgesOf(fakeTown);
	    } catch (IllegalArgumentException e) {
	    
	    }
	}


	@Test
	public void testVertexSet() {
		Set<Town> vertices = graph.vertexSet();
		assertTrue(vertices.contains(townA));
		assertTrue(vertices.contains(townB));
	}

	@Test
	public void testShortestPath() {
		graph.addVertex(townC);
		graph.addEdge(townA, townB, 4, "AB Road");
		graph.addEdge(townB, townC, 2, "BC Road");
		graph.addEdge(townA, townC, 10, "AC Road");

		ArrayList<String> path = graph.shortestPath(townA, townC);

		assertEquals(2, path.size());
		assertEquals("A via AB Road to B 4 mi", path.get(0));
		assertEquals("B via BC Road to C 2 mi", path.get(1));
	}

}
