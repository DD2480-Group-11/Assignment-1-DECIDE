package decide.launch;

// immutable class
// member variables represent the output variables of the decide function
public final class LaunchOutput {
    public final boolean[] CMV;
    public final boolean[][] PUM;
    public final boolean[] FUV;

    public LaunchOutput(boolean[] CMV,
                        boolean[][] PUM,
                        boolean[] FUV) {
        this.CMV = CMV;
        this.PUM = PUM;
        this.FUV = FUV;
    }
}