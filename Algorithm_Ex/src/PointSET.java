/**
 * Created by Kuilin on 7/26/2015.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private Set<Point2D> point2DSet;

    public PointSET() {
        // construct an empty set of points
        point2DSet = new TreeSet<Point2D>();
    }
    public boolean isEmpty() {
        // is the set empty?
        return point2DSet.isEmpty();
    }
    public int size() {
        // number of points in the set
        return point2DSet.size();
    }
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        point2DSet.add(p);
    }
    public boolean contains(Point2D p) {
        // does the set contain point p?
        return point2DSet.contains(p);
    }
    public void draw() {
        // draw all points to standard draw
        for(Point2D p : point2DSet) p.draw();
    }
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        List<Point2D> point2DList = new LinkedList<Point2D>();
        for(Point2D p : point2DSet) {
            if(rect.distanceTo(p) == 0.0) point2DList.add(p);
        }
        return point2DList;
    }
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        if (point2DSet == null || point2DSet.size() == 0) return null;
        Point2D nn = null;
        double dist = Double.MAX_VALUE;
        for(Point2D cp : point2DSet) {
            double cd = cp.distanceTo(p);
            if(cd < dist) {
                dist = cd;
                nn = cp;
            }
        }
        return nn;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}
