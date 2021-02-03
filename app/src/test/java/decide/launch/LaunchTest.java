package decide.launch;

import decide.utils.Point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

    @Test
    @DisplayName("Launch::decide::returns expected output given valid input.")
    public void testDecideCorrectness1() {

                                // justification based on point values:
        double length1 = 1;     // makes LIC 0,7 true
        double radius1 = 1;     // makes LIC 1 true
        double epsilon = 0.0;   // makes LIC 2 true
        double area1 = 5;       // makes LIC 3 false (area=0 < area1)
        int qPts = 1;           // makes LIC 4 false because only 1 point, so no points in more than 1 QUADS
        int quads = 1;          // ^
        double dist = 1;        // makes LIC 6 false because it lies on line
        int nPts = 3;           // with length1=1 and kPts makes LIC 7 true 
        int kPts = 1;           // ^
        int aPts = 1;           // makes LIC 8 true
        int bPts = 1;           // ^
        int cPts = 1;           // makes LIC 9 true, same reasoning as LIC 2
        int dPts = 1;           // ^
        int ePts = 1;           // LIC 10 false, similar reasoning as LIC 3
        int fPts = 1;           // ^
        int gPts = 1;           // LIC 11 false, similar reasoning as LIC 5
        double length2 = 10;    // makes LIC 12 true
        double radius2 = 10;    // makes LIC 13 true
        double area2 = 10;      // LIC 14 false because area1 > resulting area
        Parameters params = new Parameters( length1, radius1, epsilon, area1, qPts,
                                            quads, dist, nPts, kPts, aPts, 
                                            bPts, cPts, dPts, ePts, fPts,
                                            gPts, length2, radius2, area2);
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(2.0, 2.0),
                            new Point(3.0, 3.0),
                            new Point(4.0, 4.0)};

        Connector[][] lcm = new Connector[15][15]; 

        // set all lcm elements to NOTUSED
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                lcm[i][j] = Connector.NOTUSED;
            }
        }

        boolean[] puv = new boolean[15];    // all elements are false

        LaunchInput input = new LaunchInput(points, params, lcm, puv);
        LaunchOutput output = Launch.decide(input);

        boolean[] expected_cmv = {  true,   // LIC 0
                                    true,   // LIC 1 
                                    true,   // LIC 2
                                    false,  // LIC 3 
                                    false,  // LIC 4 
                                    false,  // LIC 5 false because ascending x values
                                    false,  // LIC 6
                                    true,   // LIC 7
                                    true,   // LIC 8
                                    true,   // LIC 9
                                    false,  // LIC 10
                                    false,  // LIC 11
                                    true,   // LIC 12
                                    true,   // LIC 13
                                    false   // LIC 14
                                    };
                                    
        boolean[][] expected_pum = new boolean[15][15];
        
        // expects all PUM elements to be true because all connectors have value NOTUSED
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                expected_pum[i][j] = true;
            }
        }
        
        boolean[] expected_fuv = new boolean[15];
        
        // expects all fuv elements to be true as all puv values are false and all pum values are true
        for(int i = 0; i < 15; i++) {
            expected_fuv[i] = true;    
        }

        assertAll(  () -> assertArrayEquals(expected_cmv, output.CMV),
                    () -> assertArrayEquals(expected_fuv, output.FUV));
                    
        for(int i = 0; i < 15; i++)
            assertArrayEquals(expected_pum[i], output.PUM[i]);
    }

    @Test
    @DisplayName("Launch::decide::returns expected output given valid input.Main procedure test 2")
    public void testDecideCorrectness2() {

                                // justification based on point values:
        double length1 = 1.4;     // makes LIC 0,7 true
        double radius1 = 0.5;     // makes LIC 1 true
        double epsilon = 0.4*Math.PI;   // makes LIC 2 true (angle=90 < PI - epsilon) 
        double area1 = 0.5;       // makes LIC 3 true (area=1 > area1)
        int qPts = 3;           // makes LIC 4 false because The points totally lie in the first quadrant.
        int quads = 1;          // ^
        double dist = 0.8;        // makes LIC 6 true. when the point array is point 2,3,4, the distance is 1.
        int nPts = 3;           //makes LIC 6 true.^  
        int kPts = 3;           // with length1=1.4 and kPts=3 to makes LIC 7 true
        int aPts = 1;           // makes LIC 8 true
        int bPts = 1;           // ^
        int cPts = 1;           // makes LIC 9 true, same reasoning as LIC 2
        int dPts = 1;           // ^
        int ePts = 1;           // LIC 10 true, similar reasoning as LIC 3
        int fPts = 1;           // ^
        int gPts = 2;           // LIC 11 true. the location of Point 2 and 5 can verify it.
        double length2 = 5;    // makes LIC 12 true
        double radius2 = 3;    // makes LIC 13 true
        double area2 = 5;      // LIC 14 true. The triangle formed by point 1,3,5 have a area of 4, <5
        Parameters params = new Parameters( length1, radius1, epsilon, area1, qPts,
                                            quads, dist, nPts, kPts, aPts, 
                                            bPts, cPts, dPts, ePts, fPts,
                                            gPts, length2, radius2, area2);
        Point[] points = {  new Point(1.0, 1.0),
                            new Point(2.0, 2.0),
                            new Point(3.0, 3.0),
                            new Point(2.0, 4.0),
                            new Point(1.0, 5.0)};

        Connector[][] lcm = new Connector[15][15]; 

        // set all lcm elements to NOTUSED
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                lcm[i][j] = Connector.NOTUSED;
            }
        }

        boolean[] puv = new boolean[15];    // all elements are false

        LaunchInput input = new LaunchInput(points, params, lcm, puv);
        LaunchOutput output = Launch.decide(input);

        boolean[] expected_cmv = {  true,   // LIC 0
                                    true,   // LIC 1 
                                    true,   // LIC 2
                                    true,  // LIC 3 
                                    false,  // LIC 4 
                                    true,  // LIC 5 
                                    true,  // LIC 6
                                    true,   // LIC 7
                                    true,   // LIC 8
                                    true,   // LIC 9
                                    true,  // LIC 10
                                    true,  // LIC 11
                                    true,   // LIC 12
                                    true,   // LIC 13
                                    true   // LIC 14
                                    };
                                    
        boolean[][] expected_pum = new boolean[15][15];
        
        // expects all PUM elements to be true because all connectors have value NOTUSED
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                expected_pum[i][j] = true;
            }
        }
        
        boolean[] expected_fuv = new boolean[15];
        
        // expects all fuv elements to be true as all pum values are true
        for(int i = 0; i < 15; i++) {
            expected_fuv[i] = true;    
        }

        assertAll(  () -> assertArrayEquals(expected_cmv, output.CMV),
                    () -> assertArrayEquals(expected_fuv, output.FUV));
                    
        for(int i = 0; i < 15; i++)
            assertArrayEquals(expected_pum[i], output.PUM[i]);
    }

    @Test
    @DisplayName("Launch::calculatePUM::test ORR connector")
    /*
     * Only first element in CMV is true and all LCM elements are ORR.
     * This should only make the entire first row and entire first column of PUM be true values
     */
    public void testCalculatePumCorrectnessORR() {

        int size = 15;
        boolean[] cmv = new boolean[size];
        Connector[][] lcm = new Connector[size][size];

        cmv[0] = true;  // in pum: entire first row true and entire first column true

        // set all lcm elements to ORR
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                lcm[i][j] = Connector.ORR;
            }
        }

        boolean[][] actual = Launch.calculatePUM(cmv, lcm);

        boolean[][] expected = new boolean[size][size];

        for(int i = 0; i < size; i++) {     // t t t ...
            expected[0][i] = true;               // t f f ...
            expected[i][0] = true;               // t f f ...
        }                                   // ...

        for(int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    @DisplayName("Launch::calculatePUM::test ANDD connector")
    /*
     * Only first and second element in CMV is set to true and all LCM elements are ANDD
     * This should only make pum[0][0], pum[0][1], pum[1][0], pum[1][1] be set to true and rest false
     */
    public void testCalculatePumCorrectnessANDD() {

        int size = 15;
        boolean[] cmv = new boolean[size];
        Connector[][] lcm = new Connector[size][size];

        cmv[0] = true;
        cmv[1] = true;

        // set all lcm elements to ANDD
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                lcm[i][j] = Connector.ANDD;
            }
        }

        boolean[][] actual = Launch.calculatePUM(cmv, lcm);

        boolean[][] expected = new boolean[size][size];

        expected[0][0] = true;     // t t f ...
        expected[0][1] = true;     // t t f ...
        expected[1][0] = true;     // f f f ...
        expected[1][1] = true;     // ...

        for(int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    @Test
    @DisplayName("Launch::calculatePUM::test NOTUSED connector")
    /*
     * All lcm elements are set to NOTUSED, so all elements in PUM should be true
     */
    public void testCalculatePumCorrectnessNOTUSED() {

        int size = 15;
        boolean[] cmv = new boolean[size];
        Connector[][] lcm = new Connector[size][size];

        // set all lcm elements to ANDD
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                lcm[i][j] = Connector.NOTUSED;
            }
        }

        boolean[][] actual = Launch.calculatePUM(cmv, lcm);

        boolean[][] expected = new boolean[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                expected[i][j] = true;
            }
        }

        for(int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }
  
    @Test
    @DisplayName("Launch::shouldLaunch::produces correct output for appropriate input")
    void testShouldLaunchSatisfied() {
        boolean[] fuv = new boolean[15];
        Arrays.fill(fuv, true);
        assertTrue(Launch.shouldLaunch(fuv));
    }

    @Test
    @DisplayName("Launch::shouldLaunch::produces correct output for inappropriate input")
    void testShouldLaunchNotSatisfied() {
        boolean[] fuv = new boolean[15];
        assertFalse(Launch.shouldLaunch(fuv));
        fuv[14] = true;
        assertFalse(Launch.shouldLaunch(fuv));
        fuv[0] = true;
        assertFalse(Launch.shouldLaunch(fuv));
        Arrays.fill(fuv, true);
        fuv[0] = false;
        assertFalse(Launch.shouldLaunch(fuv));
    }

    @DisplayName("Launch::calculateFUV::produces correct output for appropriate input")
    void testCalculateFUVSatisfied() {
        boolean[][] pum = new boolean[15][15];
        boolean[] puv = new boolean[15];
        for (boolean bool : Launch.calculateFUV(pum, puv)) {
            assertTrue(bool);
        }
        for (boolean[] col : pum) {
            Arrays.fill(col, true);
        }
        for (boolean bool : Launch.calculateFUV(pum, puv)) {
            assertTrue(bool);
        }
        Arrays.fill(puv, true);

        for (boolean bool : Launch.calculateFUV(pum, puv)) {
            assertTrue(bool);
        }
    }

    @Test
    @DisplayName("Launch::calculateFUV::produces correct output for inappropriate input")
    void testCalculateFUVNotSatisfied() {
        boolean[][] pum = new boolean[15][15];
        boolean[] puv = new boolean[15];
        Arrays.fill(puv, true);
        for (boolean bool : Launch.calculateFUV(pum, puv)) {
            assertFalse(bool);
        }
        for (boolean[] col : pum) {
            Arrays.fill(col, true);
            col[0] = false;
        }
        boolean[] calculateFUV = Launch.calculateFUV(pum, puv);
        for (int i = 0; i < calculateFUV.length; i++) {
            boolean bool = calculateFUV[i];
            if (i == 0) {
                assertTrue(bool);
            } else {
                assertFalse(bool);
            }
        }
    }

    @Test
    @DisplayName("Launch::calculateCMV::returns expected boolean array based on satisfied LICs") 
    /*
     * Set points and parameter values so that they are equal to expected CMV.
     */
    void testCalculateCMVCorrectness() {
                                // justification based on point values:
        double length1 = 1;     // makes LIC 0,7 true
        double radius1 = 1;     // makes LIC 1 true
        double epsilon = 0.0;   // makes LIC 2 true
        double area1 = 5;       // makes LIC 3 false (area=0 < area1)
        int qPts = 1;           // makes LIC 4 false because only 1 point, so no points in more than 1 QUADS
        int quads = 1;          // ^
        double dist = 1;        // makes LIC 6 false because it lies on line
        int nPts = 3;           // with length1=1 and kPts makes LIC 7 true 
        int kPts = 1;           // ^
        int aPts = 1;           // makes LIC 8 true
        int bPts = 1;           // ^
        int cPts = 1;           // makes LIC 9 true, same reasoning as LIC 2
        int dPts = 1;           // ^
        int ePts = 1;           // LIC 10 false, similar reasoning as LIC 3
        int fPts = 1;           // ^
        int gPts = 1;           // LIC 11 false, similar reasoning as LIC 5
        double length2 = 10;    // makes LIC 12 true
        double radius2 = 10;    // makes LIC 13 true
        double area2 = 10;      // LIC 14 false because area1 > resulting area
        Parameters params = new Parameters( length1, radius1, epsilon, area1, qPts,
                                            quads, dist, nPts, kPts, aPts, 
                                            bPts, cPts, dPts, ePts, fPts,
                                            gPts, length2, radius2, area2);
        Point[] points = {  new Point(0.0, 0.0),
                            new Point(1.0, 1.0),
                            new Point(2.0, 2.0),
                            new Point(3.0, 3.0),
                            new Point(4.0, 4.0)};

        boolean[] expected_cmv = {  true,   // LIC 0
                                    true,   // LIC 1 
                                    true,   // LIC 2 
                                    false,  // LIC 3 
                                    false,  // LIC 4 
                                    false,  // LIC 5 false because ascending x values
                                    false,  // LIC 6
                                    true,   // LIC 7
                                    true,   // LIC 8
                                    true,   // LIC 9
                                    false,  // LIC 10
                                    false,  // LIC 11 false because ascending x values
                                    true,   // LIC 12
                                    true,   // LIC 13
                                    false   // LIC 14
                                    };

        boolean[] result_cmv = Launch.calculateCMV(points, params);

        assertArrayEquals(expected_cmv, result_cmv);
    }
}
