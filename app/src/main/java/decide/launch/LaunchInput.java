package decide.launch;

import decide.utils.Point;

// immutable class containing the input variables for "decide" function
public final class LaunchInput {
    public final Point[] points;
    public final Parameters params;
    public final Connectors[][] lcm;
    public final boolean[] puv;

    public LaunchInput( Point[] points,
                        Parameters params,
                        Connectors[][] lcm,
                        boolean[] puv) {
        this.points = points;
        this.params = params;
        this.lcm = lcm;
        this.puv = puv;
    }
}