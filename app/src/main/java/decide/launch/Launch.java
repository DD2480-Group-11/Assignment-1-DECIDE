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

    private static boolean[][] calculatePUM(boolean[] cmv, Connector[][] lcm) {
        // TODO
        return null;
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