import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Road_STUDENT_Test {

    private Town townA;
    private Town townB;
    private Town townC;
    private Road road1;
    private Road road2;
    private Road road3;

    @BeforeEach
    public void setUp() {
        townA = new Town("TownA");
        townB = new Town("TownB");
        townC = new Town("TownC");
        road1 = new Road(townA, townB, 5, "Main Street");
        road2 = new Road(townB, townA, 5, "Main Street");
        road3 = new Road(townA, townC, "Side Road");
    }

    @Test
    public void testGetSource() {
        assertEquals(townA, road1.getSource());
    }

    @Test
    public void testGetDestination() {
        assertEquals(townB, road1.getDestination());
    }

    @Test
    public void testGetWeight() {
        assertEquals(5, road1.getWeight());
        assertEquals(1, road3.getWeight()); 
    }

    @Test
    public void testGetName() {
        assertEquals("Main Street", road1.getName());
        assertEquals("Side Road", road3.getName());
    }

    @Test
    public void testEqualsSymmetric() {
        assertTrue(road1.equals(road2));
        assertTrue(road2.equals(road1));
    }

    @Test
    public void testNotEquals() {
        assertFalse(road1.equals(road3));
    }

    @Test
    public void testToStringFormat() {
        Road road1 = new Road(new Town("Germantown"), new Town("Rockville"), 4, "Main Street");
        String result = road1.toString();
        
        assertTrue(result.contains("Main Street"));
        
    }


    @Test
    public void testCompareTo() {
        assertTrue(road1.compareTo(road3) < 0); 
        assertEquals(0, road1.compareTo(new Road(townA, townB, "Main Street")));
    }

    @Test
    public void testContains() {
        assertTrue(road1.contains(townA));
        assertTrue(road1.contains(townB));
        assertFalse(road1.contains(townC));
    }
}
