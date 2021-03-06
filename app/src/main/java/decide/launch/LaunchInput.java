package decide.launch;

import decide.utils.Point;

// immutable class containing the input variables for "decide" function
public final class LaunchInput {
    public final Point[] POINTS;
    public final Parameters PARAMS;
    public final Connector[][] LCM;
    public final boolean[] PUV;

    public LaunchInput( Point[] POINTS,
                        Parameters PARAMS,
                        Connector[][] LCM,
                        boolean[] PUV) {
        this.POINTS = POINTS;
        this.PARAMS = PARAMS;
        this.LCM = LCM;
        this.PUV = PUV;
    }
}