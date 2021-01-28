package decide.launch;

// immutable parameter class based on specification
public final class Parameters {
    private double length1; // length in LICs 0, 7, 12
    private double radius1; // radius in LICs 1, 8, 13
    private double epsilon; // deviation from PI in LICs 2, 9
    private double area1;   // area in LICs 3, 10, 14
    private int qPts;       // no. of consecutive points in LIC 4
    private int quads;      // no. of quadrants in LIC 4
    private double dist;    // distance in LIC 6
    private int nPts;       // no. of consecutive pts in LIC 6
    private int kPts;       // no. of int pts in LICs 7, 12
    private int aPts;       // no. of int pts in LICs 8, 13
    private int bPts;       // no. of int pts in LICs 8, 13 
    private int cPts;       // no. of int pts in LICs 9
    private int dPts;       // no. of int pts in LICs 9
    private int ePts;       // no. of int pts in LICs 10, 14
    private int fPts;       // no. of int pts in LICs 10, 14
    private int gPts;       // no. of int pts in LICs 11
    private double length2; // max length in LIC 12
    private double radius2; // max radius in LIC 13
    private double area2;   // max area in LIC 14

    public Parameters(double length1, 
                        double radius1,
                        double epsilon, 
                        double area1,
                        int qPts, 
                        int quads, 
                        double dist,
                        int nPts, 
                        int kPts, 
                        int aPts,
                        int bPts, 
                        int cPts, 
                        int dPts,
                        int ePts, 
                        int fPts, 
                        int gPts,
                        double length2, 
                        double radius2,
                        double area2) {

        this.length1 = length1;
        this.radius1 = radius1;
        this.epsilon = epsilon;
        this.area1 = area1;
        this.qPts = qPts;
        this.quads = quads;
        this.dist = dist;
        this.nPts = nPts;
        this.kPts = kPts;
        this.aPts = aPts;
        this.bPts = bPts;
        this.cPts = cPts;
        this.dPts = dPts;
        this.ePts = ePts;
        this.fPts = fPts;
        this.gPts = gPts;
        this.length2 = length2;
        this.radius2 = radius2;
        this.area2 = area2;
    }
}