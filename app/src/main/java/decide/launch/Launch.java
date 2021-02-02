package decide.launch;

import decide.utils.Point;

public class Launch {

    // evaluates whether should launch and outputs to standard output
    // returns output as according to specification
    public static LaunchOutput decide(LaunchInput input) {

        boolean[] cmv = calculateCMV(input.PARAMS);
        boolean[][] pum = calculatePUM(cmv, input.LCM);
        boolean[] fuv = calculateFUV(pum);

        LaunchOutput output = new LaunchOutput(cmv, pum, fuv);

        if(shouldLaunch(fuv))
            System.out.println("YES");
        System.out.println("NO");

        return output;
    }

    //uses conditions in LIC class
    private static boolean[] calculateCMV(Parameters params) {
        // TODO
        return null;
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

    private static boolean[] calculateFUV(boolean[][] pum) {
        // TODO
        return null;
    }

    private static boolean shouldLaunch(boolean[] fuv) {
        // TODO
        return false;
    }
}