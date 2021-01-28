package decide.launch;

// immutable class
public final class LaunchOutput {
    private final boolean[] cmv;
    private final boolean[][] pum;
    private final boolean[] fuv;

    public LaunchOutput(boolean[] cmv,
                        boolean[][] pum,
                        boolean[] fuv) {
        this.cmv = cmv;
        this.pum = pum;
        this.fuv = fuv;
    }
}