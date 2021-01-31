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
    @DisplayName("LIC::isCondition0::LIC 0 is satisfied.")
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
    @DisplayName("LIC:isCondition0::LIC 0 is not satisfied.")
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
    @DisplayName("LIC::isCondition2::LIC 2 fails if outside range (0 <= epsilon < pi)")
    //tests that isCondition2 fails if epsilon value outside of range
    void testCondition2Range() {
        Point[] points = {new Point(0.0, 0.0)};
        double negEpsilon = -1.0;
        double outOfMaxRangeEpsilon = 3.5;

        assertAll(  "Assert within range (0 <= epsilon < pi)",
                    () -> assertFalse(LIC.isCondition2(points, negEpsilon)),
                    () -> assertFalse(LIC.isCondition2(points, outOfMaxRangeEpsilon)));
    }

    @Test
    @DisplayName("LIC::isCondition2::LIC 2 fails when point a coincides with vertex.")
    void testCondition2AngleUndefined1() {
        Point[] points = {  new Point(1.0, 1.0),    // coincides with vertex
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0)};
        double epsilon = 0.0;

        boolean result = LIC.isCondition2(points, epsilon);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition2::LIC 2 fails when point b coincides with vertex.")
    void testCondition2AngleUndefined2() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(1.0, 1.0)};   // coincides with vertex
        double epsilon = 0.0;

        boolean result = LIC.isCondition2(points, epsilon);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition2::LIC 2 is true when condition (angle < (pi - epsilon)) is satisfied")
    void testCondition2ConditionSatisfied1() {
        Point[] points = {  new Point(3.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(3.0, 1.15)};    // angle approx. 2.56 degrees
        double epsilon = 0.1;

        boolean result = LIC.isCondition2(points, epsilon);

        assertTrue(true);
    }

    @Test
    @DisplayName("LIC::isCondition2::LIC 2 is true when condition (angle > (pi + epsilon)) is satisfied")
    void testCondition2ConditionSatisfied2() {
        Point[] points = {  new Point(2.0, 2.0),
                            new Point(15.0, 3.0),
                            new Point(5.0, 8.0)};    // angle approx. 59 degrees
        double epsilon = 2.5;

        boolean result = LIC.isCondition2(points, epsilon);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition2::LIC 2 is false when condition is not satisfied.")
    void testCondition2ConditionNotSatisfied() {
        Point[] points = {  new Point(3.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(3.0, 1.15)};      // angle approx. 2.56 degrees
        double epsilon = 2.5;                           // makes angle > (pi - epsilon)

        boolean result = LIC.isCondition2(points, epsilon);

        assertFalse(result);
    }
    @Test
    @DisplayName("LIC::isCondition3::LIC 3 is satisfied.")
    /**
     * Test if isCondition3 is true when the area meet the requirement.
     */
    public void testCondition3Satisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(5.0,1.0), new Point(3.0,3.0)};
        assertEquals(true, LIC.isCondition3(1.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition3::LIC 3 is not satisfied.")
    /**
     * Test if isCondition3 is false when the area less than the requirement.
     */
    public void testCondition3NotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(5.0,1.0), new Point(3.0,3.0)};
        assertEquals(false, LIC.isCondition3(5.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition4::LIC 4 is not satisfied.")
    /*
     * Tests whether isCondition4() is false when 3 consecutive points do not belong in the appropriate amount
     * quadrants ( in this case all points are in quadrant 1, which is not more than 1 quadrant according to
     * specification.
     */
    public void testCondition4NotSatisfied() {
        Point[] points = {new Point(1.0, 1.0), new Point(1.2, 1.0), new Point(3.0, 3.0)};
        assertEquals(false, LIC.isCondition4(3, 1, points));
    }

    @Test
    @DisplayName("LIC::isCondition4::LIC 4 is satisfied.")
    /*
     * Tests whether isCondition4() is true when 3 consecutive points belong in the appropriate amount
     * quadrants (more than one quadrant in this case).
     */
    public void testCondition4Satisfied() {
        Point[] points = {new Point(1.0, 1.0), new Point(1.2, 1.0), new Point(-2.0, 3.0)};
        assertTrue(LIC.isCondition4(3, 1, points));
    }

    @Test
    @DisplayName("LIC::isCondition7::LIC 7 returns false if (NUMPOINTS < 3).")
    public void testCondition7NumPointsRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0)};
        int kPts = 1;
        int length = 0;

        boolean result = LIC.isCondition7(points, kPts, length);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition7::LIC 7 returns false if (K_PTS < 1).")
    public void testCondition7MinSeparationRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = 0;
        int length = 1;

        boolean result = LIC.isCondition7(points, kPts, length);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition7::LIC 7 returns false if (K_PTS > (NUMPOINTS-2)).")
    public void testCondition7MaxSeparationRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = points.length-1;
        int length = 1;

        boolean result = LIC.isCondition7(points, kPts, length);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition7::LIC 7 returns true if condition is satisfied.") 
    public void testCondition7Satisfied() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};     // greatest dist approx. equals 12.7
        int kPts = 1;
        int length = 10;

        boolean result = LIC.isCondition7(points, kPts, length);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition7::LIC 7 returns false if condition is not satisfied.") 
    public void testCondition7NotSatisfied() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};     // greatest dist approx. equals 12.7
        int kPts = 1;
        int length = 15;

        boolean result = LIC.isCondition7(points, kPts, length);

        assertFalse(result);
    }
}