package decide.launch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

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
}
