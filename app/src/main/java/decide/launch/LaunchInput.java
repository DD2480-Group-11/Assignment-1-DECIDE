package decide.launch;

import decide.utils.Point;

// immutable class containing the input variables for "decide" function
public final class LaunchInput {
    private final Point[] points;
    private final Parameters params;
    private final Connectors[][] lcm;
    private final boolean[] puv;

    public LaunchInput( Point[] points,
                        Parameters params,
                        Connectors[][] lcm,
                        boolean[] puv) {
        this.points = points;
        this.params = params;
        this.lcm = lcm;
        this.puv = puv;
    }

    public Point[] getPoints() {
        return points;
    }

    public Parameters getParams() {
        return params;
    }

    public Connectors[][] getLCM() {
        return lcm;
    }

    public boolean[] getPUV() {
        return puv;
    }
}