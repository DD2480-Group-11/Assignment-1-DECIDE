package decide.launch;

// immutable parameter class based on specification
public final class Parameters {
    public final double LENGTH_1; // length in LICs 0, 7, 12
    public final double RADIUS_1; // radius in LICs 1, 8, 13
    public final double EPSILON;  // deviation from PI in LICs 2, 9
    public final double AREA_1;   // area in LICs 3, 10, 14
    public final int Q_PTS;       // no. of consecutive points in LIC 4
    public final int QUADS;       // no. of quadrants in LIC 4
    public final double DIST;     // distance in LIC 6
    public final int N_PTS;       // no. of consecutive pts in LIC 6
    public final int K_PTS;       // no. of int pts in LICs 7, 12
    public final int A_PTS;       // no. of int pts in LICs 8, 13
    public final int B_PTS;       // no. of int pts in LICs 8, 13 
    public final int C_PTS;       // no. of int pts in LICs 9
    public final int D_PTS;       // no. of int pts in LICs 9
    public final int E_PTS;       // no. of int pts in LICs 10, 14
    public final int F_PTS;       // no. of int pts in LICs 10, 14
    public final int G_PTS;       // no. of int pts in LICs 11
    public final double LENGTH_2; // max length in LIC 12
    public final double RADIUS_2; // max radius in LIC 13
    public final double AREA_2;   // max area in LIC 14

    public Parameters(  double LENGTH_1, 
                        double RADIUS_1,
                        double EPSILON, 
                        double AREA_1,
                        int Q_PTS, 
                        int QUADS, 
                        double DIST,
                        int N_PTS, 
                        int K_PTS, 
                        int A_PTS,
                        int B_PTS, 
                        int C_PTS, 
                        int D_PTS,
                        int E_PTS, 
                        int F_PTS, 
                        int G_PTS,
                        double LENGTH_2, 
                        double RADIUS_2,
                        double AREA_2) {
        
        this.LENGTH_1 = LENGTH_1;
        this.RADIUS_1 = RADIUS_1;
        this.EPSILON = EPSILON;
        this.AREA_1 = AREA_1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.G_PTS = G_PTS;
        this.LENGTH_2 = LENGTH_2;
        this.RADIUS_2 = RADIUS_2;
        this.AREA_2 = AREA_2;
    }
}