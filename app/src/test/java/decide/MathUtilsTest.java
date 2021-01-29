package decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import decide.utils.*;

public class MathUtilsTest {
    
    @Test
    /**
     * Test if calculateArea can calculate the area correctly.
     */
    public void calculateAreaTest(){
        Point a = new Point(1,1);
        Point b = new Point(3,1);
        Point c = new Point(2,2);
        assertEquals(1.0, MathUtils.calculateArea(a, b, c),0.0001);
    }
    @Test
    /**
     * Test if calculateLength can calculate the length correctly.
     */
    public void calculateLengthTest(){
        Point a = new Point(1,1);
        Point b = new Point(2,1);
        assertEquals(1.0, MathUtils.calculateLength(a, b),0.0001);
    }
}
