package decide.launch;

import decide.utils.Point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class LICTest {

    @Test
    @DisplayName("LIC::isCondition0::LIC 0 does not accept (length < 0).")
    // tests that isCondition0 returns false if the length provided is less than 0
    void testCondition0NegativeLength() {
        Point[] points = {  new Point(5.0, 5.0),
                            new Point(10.0, 10.0),
                            new Point(20.0, 20.0),  
                            new Point(35.5, 60.5)};
        double length = -1.0;

        boolean result = LIC.isCondition0(points, length);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition0:condition is satisfied.")
    // tests that isCondition0 returns true if there are two consecutive data
    // points with a distance between eachother greater than length
    void testCondition0Satisfied() {
        // dist between last two elements approx. equals 43.36
        Point[] points = {  new Point(5.0, 5.0),
                            new Point(10.0, 10.0),
                            new Point(20.0, 20.0),
                            new Point(35.5, 60.5)};
        double length = 14.0;

        boolean result = LIC.isCondition0(points, length);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition0:condition is not satisfied.")
    // tests that isCondition0 returns false if there are not two consecutive 
    // data points with a distance between eachother greater than length
    void testCondition0NotSatisfied() {
        // dist between last two elements approx. equals 43.36
        Point[] points = {  new Point(5.0, 5.0),
                            new Point(10.0, 10.0),
                            new Point(20.0, 20.0),
                            new Point(35.5, 60.5)};
        double length = 44.0;

        boolean result = LIC.isCondition0(points, length);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition1::LIC 1 fails if condition is not met.")
    //tests that isCondition1 fails if if all consecutive points can be inside the circle
    void testCondition1NotSatisfied() {
        //Equilateral triangle
        Point[] eqTriangle = {  new Point(0.0, 0.0),
                                new Point(3.0,0.0),
                                new Point(1.5,2.6)}; //Should yield circumcircle radius of ~1.73
        double radius1 = 2;
        boolean result1 = LIC.isCondition1(eqTriangle, radius1);

        //Right triangle
        Point[] rTriangle = {   new Point(0.0, 0.0),
                                new Point(5.0,0.0),
                                new Point(0.0,5.0)}; //Should yield circumcircle radius of ~3.5
        double radius2 = 4;
        boolean result2 = LIC.isCondition1(rTriangle, radius2);

         //Obtuse triangle
         Point[] oTriangle = {  new Point(1.0, 0.0),
                                new Point(5.0,0.0),
                                new Point(0.0,5.0)}; //Should yield circumcircle radius of ~3.6
                    double radius3 = 4;
                    boolean result3 = LIC.isCondition1(oTriangle, radius3);

        
        assertAll(  () -> assertFalse(result1),
                    () -> assertFalse(result2),
                    () -> assertFalse(result3));
    }

}