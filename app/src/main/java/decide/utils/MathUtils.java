package decide.utils;
import decide.launch.*;

public class MathUtils {
    public MathUtils(){

    }

/**
 * Function name: calculateArea
 * <p>
 * Using Heron's formula to calculate the area of triangle.
 * 
 * @param a the first point
 * @param b the second point
 * @param c the third point
 * @return the area of the triangle
 * @author Yu Zhou
 */
    public static double calculateArea(Point a, Point b, Point c) {
       double lengthA = calculateLength(a, b);
       double lengthB = calculateLength(b, c);
       double lengthC = calculateLength(c, a);
       double halfPerimeter = (lengthA + lengthB + lengthC)/2.0;
       double Area3 = halfPerimeter*(halfPerimeter-lengthA)*(halfPerimeter-lengthB)*(halfPerimeter-lengthC);
       if(Area3<0 || Area3==0){
           return 0.0;
       }
       double SquareRootOfArea3 = Math.sqrt(Area3);
       return SquareRootOfArea3;
    }


    /**
     * 
     * @param a the first point
     * @param b the second point
     * @return the length between the points
     */
    public static double calculateLength(Point a, Point b){
        double length = Math.sqrt(Double.valueOf((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY())));
        return length;
    }






}
