package decide.launch;

// immutable class
// member variables represent the output variables of the decide function
public final class LaunchOutput {
    private final boolean[] CMV;
    private final boolean[][] PUM;
    private final boolean[] FUV;

    public LaunchOutput(boolean[] CMV,
                        boolean[][] PUM,
                        boolean[] FUV) {
        this.CMV = CMV;
        this.PUM = PUM;
        this.FUV = FUV;
    }
}