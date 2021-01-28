package decide.launch;

import decide.utils.Point;

public class Launch {

    // evaluates whether should launch and outputs to standard output
    // returns output as according to specification
    public static LaunchOutput decide(LaunchInput input) {

        boolean[] cmv = calculateCMV();
        boolean[][] pum = calculatePUM();
        boolean[] fuv = calculateFUV();

        LaunchOutput output = new LaunchOutput(cmv, pum, fuv);

        if(shouldLaunch(fuv))
            System.out.println("YES");
        System.out.println("NO");

        return output;
    }

    //uses conditions in LIC class
    private static boolean[] calculateCMV() {
        // TODO: add appropriate method parameters
        return null;
    }

    private static boolean[][] calculatePUM() {
        // TODO: add appropriate method parameters
        return null;
    }

    private static boolean[] calculateFUV() {
        // TODO: add appropriate method parameters
        return null;
    }

    private static boolean shouldLaunch(boolean[] fuv) {
        // TODO: add appropriate method parameters
        return false;
    }
}