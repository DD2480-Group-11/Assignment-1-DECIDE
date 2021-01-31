package decide.launch;

import decide.utils.Point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    @DisplayName("Point::getQuadrant::behaves as according to specification.")
    /*
     * Tests whether getQuadrant() is true for the example outlined in the specification.
     */
    public void testGetQuadrant() {
        assertEquals(1, Point.getQuadrant(new Point(0, 0)));
        assertEquals(2, Point.getQuadrant(new Point(-1, 0)));
        assertEquals(3, Point.getQuadrant(new Point(0, -1)));
        assertEquals(1, Point.getQuadrant(new Point(0, 1)));
        assertEquals(1, Point.getQuadrant(new Point(1, 1)));
        assertEquals(1, Point.getQuadrant(new Point(1, 0)));
        assertEquals(4, Point.getQuadrant(new Point(1, -1)));

    }
}
