import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TownGraphManager_STUDENT_Test {

    private TownGraphManager manager;

    @BeforeEach
    public void setUp() {
        manager = new TownGraphManager();
        manager.addTown("Germantown");
        manager.addTown("Rockville");
        manager.addTown("Gaithersburg");
        manager.addRoad("Germantown", "Rockville", 5, "Main Street");
        manager.addRoad("Rockville", "Gaithersburg", 10, "Highway 1");
    }

    @Test
    public void testAddTown() {
        assertTrue(manager.addTown("Darnestown"));
        assertFalse(manager.addTown("Germantown")); 
    }

    @Test
    public void testContainsTown() {
        assertTrue(manager.containsTown("Germantown"));
        assertFalse(manager.containsTown("Potomac"));
    }

    @Test
    public void testGetTown() {
        Town t = manager.getTown("Rockville");
        assertNotNull(t);
        assertEquals("Rockville", t.getName());
    }

    @Test
    public void testAddRoad() {

        assertTrue(manager.addRoad("Gaithersburg", "Germantown", 7, "Loop Road"));

        try {
            manager.addRoad("Nonexistent1", "Nonexistent2", 3, "Fake Road");
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {

        }

    }



    @Test
    public void testGetRoad() {
        String roadName = manager.getRoad("Germantown", "Rockville");
        assertTrue(roadName.contains("Main Street"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertTrue(manager.containsRoadConnection("Germantown", "Rockville"));
        assertFalse(manager.containsRoadConnection("Germantown", "Gaithersburg"));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(manager.deleteRoadConnection("Rockville", "Gaithersburg", "Highway 1"));
        assertFalse(manager.containsRoadConnection("Rockville", "Gaithersburg"));
    }

    @Test
    public void testDeleteTown() {
        assertTrue(manager.deleteTown("Gaithersburg"));
        assertFalse(manager.containsTown("Gaithersburg"));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> towns = manager.allTowns();
        assertEquals(3, towns.size());
        assertTrue(towns.toString().contains("Germantown"));
        assertTrue(towns.toString().contains("Rockville"));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = manager.allRoads();
        assertEquals(2, roads.size());
        assertTrue(roads.get(0).contains("Main Street") || roads.get(1).contains("Main Street"));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = manager.getPath("Germantown", "Gaithersburg");
        assertNotNull(path);
        assertTrue(path.size() >= 2); 
    }
    @Test
    public void testPopulateTownGraph() throws IOException {
        String testData = "I-94,282;Chicago;Detroit\n" +
                          "I-55,510;Chicago;Kansas City\n";

        File tempFile = File.createTempFile("test_town_graph", ".txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(testData);
        }

        TownGraphManager newManager = new TownGraphManager();
        newManager.populateTownGraph(tempFile);

        assertTrue(newManager.containsTown("Chicago"));
        assertTrue(newManager.containsTown("Detroit"));
        assertTrue(newManager.containsTown("Kansas City"));

        assertTrue(newManager.containsRoadConnection("Chicago", "Detroit"));
        assertTrue(newManager.containsRoadConnection("Chicago", "Kansas City"));

        ArrayList<String> roads = newManager.allRoads();
        assertEquals(2, roads.size());

        boolean foundI94 = false;
        boolean foundI55 = false;
        for (String road : roads) {
            if (road.contains("I-94")) {
                foundI94 = true;
            }
            if (road.contains("I-55")) {
                foundI55 = true;
            }
        }

        assertTrue(foundI94);
        assertTrue(foundI55);
    }

}
