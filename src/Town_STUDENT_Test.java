import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Town_STUDENT_Test {
    
    private Town town1;
    private Town town2;
    private Town town3;

    @BeforeEach
    public void setUp() {
        town1 = new Town("Germantown");
        town2 = new Town("Rockville");
        town3 = new Town(town1); 
    }

    @Test
    public void testGetName() {
        assertEquals("Germantown", town1.getName());
        assertEquals("Rockville", town2.getName());
    }

    @Test
    public void testCopyConstructor() {
        assertEquals(town1.getName(), town3.getName());
        assertNotSame(town1, town3);
    }

    @Test
    public void testEquals() {
        assertTrue(town1.equals(town3));
        assertFalse(town1.equals(town2));
        assertFalse(town1.equals(null));
        assertFalse(town1.equals("NotATown"));
    }

    @Test
    public void testHashCode() {
        assertEquals(town1.hashCode(), town3.hashCode());
        assertNotEquals(town1.hashCode(), town2.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Germantown", town1.toString());
        assertEquals("Rockville", town2.toString());
    }

    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) < 0); 
        assertEquals(0, town1.compareTo(town3));
        assertTrue(town2.compareTo(town1) > 0);  
    }
}
