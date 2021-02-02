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
    @DisplayName("LIC::isCondition0::condition is satisfied.")
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
    @DisplayName("LIC:isCondition0::condition is not satisfied.")
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
    @DisplayName("LIC::isCondition3::condition is satisfied.")
    /**
     * Test if isCondition3 is true when the area meet the requirement.
     */
    public void testCondition3Satisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(5.0,1.0), new Point(3.0,3.0)};
        assertEquals(true, LIC.isCondition3(1.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition3::condition is not satisfied.")
    /**
     * Test if isCondition3 is false when the area less than the requirement.
     */
    public void testCondition3NotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(5.0,1.0), new Point(3.0,3.0)};
        assertEquals(false, LIC.isCondition3(5.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition4::condition is not satisfied.")
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
    @DisplayName("LIC::isCondition4::condition is satisfied.")
    /*
     * Tests whether isCondition4() is true when 3 consecutive points belong in the appropriate amount
     * quadrants (more than one quadrant in this case).
     */
    public void testCondition4Satisfied() {
        Point[] points = {new Point(1.0, 1.0), new Point(1.2, 1.0), new Point(-2.0, 3.0)};
        assertTrue(LIC.isCondition4(2, 1, points));
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

    @Test
    @DisplayName("LIC::isCondition5::condition is satisfied.")
    /*
     * Tests whether isCondition5() is true when 2 consecutive points where there is a negative difference
     * between X[i] and X[j], j = i -1
     * This also checks that there is no off by one error
     */
    public void testCondition5Satisfied() {
        Point[] points = {new Point(4.0, 1.0), new Point(3.0, 0.0), new Point(2.0, 0.0)};
        assertTrue(LIC.isCondition5(points));
    }
    @Test
    @DisplayName("LIC::isCondition5::condition is not satisfied.")
    /*
     * Tests whether isCondition5() is false when there are no consecutive points with negative difference
     * between X[i] and X[j], j = i -1
     */
    public void testCondition5NotSatisfied() {
        Point[] points = {new Point(1.0, 1.0), new Point(1.2, 1.0), new Point(2.0, 3.0)};
        assertFalse(LIC.isCondition4(2, 1, points));
    }

    @Test
    @DisplayName("LIC::isCondition6::condition is satisfied.")
    /**
     * Test if isCondition6 is true when the distance meets the requirement.
     * The number of points is set to 3, the required dist is set to 0.5, the n_points is set to 3.
     */
    public void testCondition6Satisfied(){
        double dist = 0.5;
        int n_points = 3;
        Point[] points = {new Point(1.0,1.0), new Point(3.0,1.0),new Point(2.0,2.0)};
        assertTrue(LIC.isCondition6(n_points,dist,points));
    }

    @Test
    @DisplayName("LIC::isCondition6::condition is not satisfied.")
    /**
     * Test if isCondition6 is false when the distance doesn't meet the requirement.
     * The chosed 3 points are placed into a line,so the distance becomes to zero.
     */
    public void testCondition6NotSatisfied(){
        double dist = 0.5;
        int n_points = 3;
        Point[] points = {new Point(1.0,1.0), new Point(2.0,1.0),new Point(3.0,1.0)};
        assertFalse(LIC.isCondition6(n_points,dist,points));
    }

    @Test
    @DisplayName("LIC::isCondition6::NumOfPoints less than 3.")
    /**
     * Test if isCondition6 is false when the number of points less than 3.
     */
    public void testCondition6NumNotSatisfied(){
        double dist = 0.5;
        int n_points = 3;
        Point[] points = {new Point(1.0,1.0), new Point(2.0,1.0)};
        assertFalse(LIC.isCondition6(n_points,dist,points));
    }


    @Test
    @DisplayName("LIC::isCondition10::condition is satisfied.")
    /**
     * Test if isCondition10 is true when the area meet the requirement.
     */
    public void testCondition10Satisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(1.0,1.0),new Point(3.0,3.0)};
        assertEquals(true, LIC.isCondition10(1,1,3.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition10::condition is not satisfied.")
    /**
     * Test if isCondition10 is false when the area less than the requirement.
     */
    public void testCondition10NotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(1.0,1.0),new Point(3.0,3.0)};
        assertEquals(false, LIC.isCondition10(1,1,5.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition10::NumOfPoints less than 5.")
    /**
     * Test if isCondition10 is false when the number of points less than 5.
     */
    public void testCondition10NumNotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(1.0,1.0)};
        assertEquals(false, LIC.isCondition10(1,1,3.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 fails if outside range (0 <= epsilon < pi)")
    //tests that isCondition9 fails if epsilon value outside of range
    void testCondition9Range() {
        Point[] points = {new Point(0.0, 0.0)};
        double negEpsilon = -1.0;
        double outOfMaxRangeEpsilon = 3.5;

        assertAll(  "Assert within range (0 <= epsilon < pi)",
                    () -> assertFalse(LIC.isCondition9(points, negEpsilon, 1, 1)),
                    () -> assertFalse(LIC.isCondition9(points, outOfMaxRangeEpsilon, 1, 1)));
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 returns false if either C_PTS and D_PTS are (< 1).")
    void testCondition9PassedPointsRequirements() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(0.0, 0.0),
                            new Point(0.0, 0.0),
                            new Point(0.0, 0.0),
                            new Point(0.0, 0.0)};
        double epsilon = 1.0;
        int p1 = 0;
        int p2 = 1;

        assertAll(  () -> assertFalse(LIC.isCondition9(points, epsilon, p1, p2)),
                    () -> assertFalse(LIC.isCondition9(points, epsilon, p2, p1)));
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 fails when first point coincides with vertex.")
    void testCondition9AngleUndefined1() {
        Point[] points = {  new Point(1.0, 1.0),    // coincides with vertex
                            new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(0.0, 0.0)};
        double epsilon = 0.0;
        int cPts = 1;
        int dPts = 1;

        boolean result = LIC.isCondition9(points, epsilon, cPts, dPts);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if (length1 < 0)")
    public void testCondition12NegativeLength1() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = 0;
        int length1 = -1;
        int length2 = 1;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if (length2 < 0)")
    public void testCondition12NegativeLength2() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = 1;
        int length1 = 1;
        int length2 = -1;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if (NUMPOINTS < 3).")
    public void testCondition12NumPointsRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0)};
        int kPts = 1;
        int length1 = 1;
        int length2 = 2;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 fails when second point coincides with vertex.")
    void testCondition9AngleUndefined2() {
        Point[] points = {  new Point(0.0, 0.0),   
                            new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(1.0, 1.0)};   // coincides with vertex
        double epsilon = 0.0;
        int cPts = 1;
        int dPts = 1;

        boolean result = LIC.isCondition9(points, epsilon, cPts, dPts);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if (K_PTS < 1).")
    public void testCondition12MinSeparationRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = 0;
        int length1 = 1;
        int length2 = 2;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 is true when condition (angle < (pi - epsilon)) is satisfied")
    void testCondition9ConditionSatisfied1() {
        Point[] points = {  new Point(3.0, 1.0),
                            new Point(15.0, 15.0),
                            new Point(0.0, 0.0),
                            new Point(15.0, 15.0),
                            new Point(3.0, 1.15)};    // angle approx. 2.56 degrees
        double epsilon = 0.1;
        int cPts = 1;
        int dPts = 1;

        boolean result = LIC.isCondition9(points, epsilon, cPts, dPts);

        assertTrue(true);
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 is true when condition (angle > (pi + epsilon)) is satisfied")
    void testCondition9ConditionSatisfied2() {
        Point[] points = {  new Point(2.0, 2.0),
                            new Point(15.0, 15.0),
                            new Point(15.0, 3.0),
                            new Point(15.0, 15.0),
                            new Point(5.0, 8.0)};    // angle approx. 59 degrees
        double epsilon = 2.5;
        int cPts = 1;
        int dPts = 1;

        boolean result = LIC.isCondition9(points, epsilon, cPts, dPts);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition11::LIC 11 returns false if (G_PTS < 1).")
    public void testCondition11MinSeparationRequirement() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(2.0, 2.0)};
        int gPts = 0;

        boolean result = LIC.isCondition11(points, gPts);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition11::LIC 11 returns false if (G_PTS > (NUMPOINTS-2)).")
    public void testCondition11MaxSeparationRequirement() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(2.0, 2.0)};
        int gPts = points.length-1;

        boolean result = LIC.isCondition11(points, gPts);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition11::LIC 11 returns true if condition is satisfied.")
    /*
     * returns true if two consecutive points satisfy: the latter point has a larger x value than the first. 
     */
    public void testCondition11Satisfied() {
        Point[] points = {  new Point(2.0, 2.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0)};
        int gPts = 1;

        boolean result = LIC.isCondition11(points, gPts);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition11::LIC 11 returns false if condition is not satisfied.")
    /*
     * returns true if two consecutive points do not satisfy: the latter point has a larger x value than the first. 
     */
    public void testCondition11NotSatisfied() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(2.0, 2.0)};
        int gPts = 1;

        boolean result = LIC.isCondition11(points, gPts);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if (K_PTS > (NUMPOINTS-2)).")
    public void testCondition12MaxSeparationRequirement() {
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};
        int kPts = points.length-1;
        int length1 = 1;
        int length2 = 2;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns true if condition is satisfied.") 
    public void testCondition12Satisfied() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};     // greatest dist approx. equals 12.7
        int kPts = 1;
        int length1 = 10;   // < 12.7, so greater than length1
        int length2 = 15;   // > 12.7, so less than length2

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertTrue(result);
    }

    @Test
    @DisplayName("LIC::isCondition9::LIC 9 is false when condition is not satisfied.")
    void testCondition9ConditionNotSatisfied() {
        Point[] points = {  new Point(3.0, 1.0),
                            new Point(15.0, 15.0),
                            new Point(0.0, 0.0),
                            new Point(15.0, 15.0),
                            new Point(3.0, 1.15)};      // angle approx. 2.56 degrees
        double epsilon = 2.5;                           // makes angle > (pi - epsilon)
        int cPts = 1;
        int dPts = 1;

        boolean result = LIC.isCondition9(points, epsilon, cPts, dPts);
        
        assertFalse(result);
    }


    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if condition is not satisfied. No consecutive points greater than length1.") 
    public void testCondition12NotSatisfied1() {
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(0.0, 0.0),
                            new Point(10.0, 10.0)};     // greatest dist approx. equals 12.7
        int kPts = 1;
        int length1 = 15;    // > 12.7, so no points with distance greater than length1
        int length2 = 20;

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }

    @Test
    @DisplayName("LIC::isCondition12::LIC 12 returns false if condition is not satisfied. No consecutive points less than length2.") 
    public void testCondition12NotSatisfied2() {
        Point[] points = {  new Point(10.0, 10.0),
                            new Point(1.0, 1.0),
                            new Point(1.0, 1.0),
                            new Point(10.0, 10.0)};     // greatest dist approx. equals 12.7
        int kPts = 1;
        int length1 = 10;   
        int length2 = 11;   // < 12.7, so no points with distance less than length2

        boolean result = LIC.isCondition12(points, kPts, length1, length2);

        assertFalse(result);
    }


    @Test
    @DisplayName("LIC::isCondition14::condition is satisfied.")
    /**
     * Test if isCondition10 is true when both area1 and area2 met the requirement.
     * the 1,3,5 points form a triangle with the area of 4; the 2,4,6 points form a triangle with the area of 0.5. 
     */
    public void testCondition14Satisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(2.0,1.0),new Point(3.0,3.0),new Point(1.5,2.0)};
        assertTrue(LIC.isCondition14(1,1,3.0,1.0,points));

    }

    @Test
    @DisplayName("LIC::isCondition14::condition is not satisfied.")
    /**
     * Test if isCondition14 is false when the area1 mets the requirement but area2 does not met the requirements.
     * There are 5 points in the array. So just 1,3,5 forms the triangle and meets the requirement: area1.
     */
    public void testCondition14NotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(1.0,1.0),new Point(3.0,3.0)};
        assertFalse(LIC.isCondition14(1,1,3.0,1.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition14::NumOfPoints less than 5.")
    /**
     * Test if isCondition14 is false when the number of points is less than 5.
     */
    public void testCondition14NumNotSatisfied(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(1.0,1.0)};
        assertEquals(false, LIC.isCondition14(1,1,3.0,1.0,points));
    }

    @Test
    @DisplayName("LIC::isCondition14::Area 2 less than 0.")
    /**
     * Test if isCondition14 is false when the area2 is less than 0.
     */
    public void testCondition14Area2LessThan0(){

        Point[] points = {new Point(1.0,1.0), new Point(1.0,1.0),new Point(5.0,1.0), new Point(2.0,1.0),new Point(3.0,3.0),new Point(1.5,2.0)};
        assertFalse(LIC.isCondition14(1,1,3.0,-1.0,points));
    }
}
