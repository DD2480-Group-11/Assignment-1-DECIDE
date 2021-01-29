package decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import decide.launch.*;
import decide.utils.*;

public class LICTest {


    @Test
    /**
     * Test if isCondition3 is correct.
     */
    public void isCondition3Test(){
        Parameters parameter = new Parameters(1);
        Point[] points = {new Point(1,1), new Point(5,1), new Point(3,3)};
        assertEquals(true, LIC.isCondition3(parameter,points));        
    }
   
    

}
