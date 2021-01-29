package decide.launch;
import decide.utils.*;


// conditions are used to calculate the CMV
public final class LIC {
    private static boolean isCondition0() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition1() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition2() {
        // TODO: add appropriate method parameters
        return false;
    }

    public static boolean isCondition3(Parameters params,Point[] points) {
        // TODO: add appropriate method parameters
        double area;

        for(int i=0;i<points.length-2;i++){
            area = MathUtils.calculateArea(points[i],points[i+1],points[i+2]);
                if(area > params.area1)
                    return true;         
            } 
        return false;
    }



    private static boolean isCondition4() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition5() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition6() {
        // TODO: add appropriate method parameters
        return false;
    }


    private static boolean isCondition7() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition8() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition9() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition10() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition11() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition12() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition13() {
        // TODO: add appropriate method parameters
        return false;
    }

    private static boolean isCondition14() {
        // TODO: add appropriate method parameters
        return false;
    }
}