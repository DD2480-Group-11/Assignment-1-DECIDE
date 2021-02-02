package decide.launch;

import decide.utils.Point;

public class Launch {

    // evaluates whether should launch and outputs to standard output
    // returns output as according to specification
    public static LaunchOutput decide(LaunchInput input) {

        boolean[] cmv = calculateCMV(input.POINTS, input.PARAMS);
        boolean[][] pum = calculatePUM(cmv, input.LCM);
        boolean[] fuv = calculateFUV(pum, input.PUV);

        LaunchOutput output = new LaunchOutput(cmv, pum, fuv);

        if(shouldLaunch(fuv))
            System.out.println("YES");
        else
            System.out.println("NO");

        return output;
    }

    //uses conditions in LIC class
    public static boolean[] calculateCMV(Point[] points, Parameters params) {

        boolean[] cmv = new boolean[15];

        if(LIC.isCondition0(points, params.LENGTH_1))
            cmv[0] = true;

        if(LIC.isCondition1(points, params.RADIUS_1))
            cmv[1] = true;

        if(LIC.isCondition2(points, params.EPSILON))
            cmv[2] = true;

        if(LIC.isCondition3(params.AREA_1, points))
            cmv[3] = true;

        if(LIC.isCondition4(params.Q_PTS, params.QUADS, points))
            cmv[4] = true;

        if(LIC.isCondition5(points))
            cmv[5] = true;

        if(LIC.isCondition6(params.N_PTS, params.DIST, points))
            cmv[6] = true;

        if(LIC.isCondition7(points, params.K_PTS, params.LENGTH_1))
            cmv[7] = true;

        if(LIC.isCondition8(points, params.RADIUS_1, params.A_PTS, params.B_PTS))
            cmv[8] = true;

        if(LIC.isCondition9(points, params.EPSILON, params.C_PTS, params.D_PTS))
            cmv[9] = true;

        if(LIC.isCondition10(params.E_PTS, params.F_PTS, params.AREA_1, points))
            cmv[10] = true;

        if(LIC.isCondition11(points, params.G_PTS))
            cmv[11] = true;

        if(LIC.isCondition12(points, params.K_PTS, params.LENGTH_1, params.LENGTH_2))
            cmv[12] = true;

        if(LIC.isCondition13(points, params.RADIUS_1, params.RADIUS_2, params.A_PTS, params.B_PTS))
            cmv[13] = true;

        if(LIC.isCondition14(params.E_PTS, params.F_PTS, params.AREA_1, params.AREA_2, points))
            cmv[14] = true;

        return cmv;
    }

    /**
     * @param cmv boolean array that represents the LICs met
     * @param lcm logical connector matrix determining the boolean operation performed on CMV elements to obtain PUM elements
     * @return Preliminary Unlocking Matrix
     */
    public static boolean[][] calculatePUM(boolean[] cmv, Connector[][] lcm) {

       	final int SIZE = 15;    // size of cmv and rows/cols of lcm and pum

        boolean[][] pum = new boolean[SIZE][SIZE];

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j  < SIZE; j++) {

                Connector operator = lcm[i][j];

                switch(operator) {
                    case NOTUSED:   pum[i][j] = true;
                                    break;

                    case ORR:       if(cmv[i] || cmv[j])
                                        pum[i][j] = true;

                    case ANDD:      if(cmv[i] && cmv[j])
                                        pum[i][j] = true;
                                    break;

                    default:        break;
                }
            }
        }

	    return pum;
    }

    public static boolean[] calculateFUV(boolean[][] pum, boolean[] puv) {
        boolean[] fuv = new boolean[15];
        for (int i = 0; i < pum.length; i += 1) {
            if (puv[i]) {
                fuv[i] = true;
                for (int j = 0; j < pum[i].length; j += 1) {
                    if (!pum[i][j]) {
                        if (j != i) {
                            fuv[i] = false;
                            break;
                        }
                    }
                }
            } else {
                fuv[i] = true;
            }
        }
        return fuv;
    }

    public static boolean shouldLaunch(boolean[] fuv) {
        for (int i = 0; i < fuv.length; i++) {
            if (!fuv[i]) {
                return false;
            }
        }
        return true;
    }
}
