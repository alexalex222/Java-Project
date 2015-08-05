/**
 * Created by Kuilin on 7/5/2015.
 */

/*************************************************************************
 * Name: Kuilin Chen
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private class SlopeOrder implements  Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if(p1 == null || p2 == null) {
                throw new NullPointerException("Cannot compare null!");
            }

            double slopeP1 = slopeTo(p1);
            double slopeP2 = slopeTo(p2);

            if(slopeP1 < slopeP2) { return -1; }
            else if(slopeP1 > slopeP2) { return 1; }
            else { return 0; }
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // null point
        if(that == null) {
            throw new NullPointerException("Cannot compare to Null!");
        }

        if(that.y == this.y && this.x == that.x) {
            // compare the same point
            return Double.NEGATIVE_INFINITY;
        } else if(that.y ==  this.y) {
            // a horizontal line
            return 0;
        } else if(that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (double) (that.y - this.y)/(that.x - this.x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        // null pointer
        if(that == null) {
            throw new NullPointerException("Cannot compare to null!");
        }

        int result;

        if(that.y == this.y) {
            // y coordinates are equal, break tie with x
            if(that.x == this.x) {
                result = 0;
            } else if(this.x < that.x) {
                result = -1;
            } else {
                result = 1;
            }
            // y coordinates are not equal, just compare based on y
        } else if(this.y < that.y) {
            result = -1;
        } else {
            result = 1;
        }

        return result;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        StdDraw.setXscale(0, 15);
        StdDraw.setYscale(0, 15);
        StdDraw.setPenRadius(0.005);

        Point first = new Point(5, 2);
        Point second = new Point(4, 2);

        first.draw();
        second.draw();

        first.drawTo(second);

        int compareResult;
        compareResult = first.compareTo(second);


        System.out.println("slopeTo:" + first.slopeTo(second));
        System.out.println("Compare result:"+compareResult);

    }
}