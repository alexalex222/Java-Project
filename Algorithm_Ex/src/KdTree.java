/**
 * Created by Kuilin on 7/26/2015.
 */
import java.util.Set;
import java.util.TreeSet;

public class KdTree {

    private Node root;
    private int numNodes;

    private class Node implements Comparable<Node> {
        private Point2D point2D;
        private Node left;
        private Node right;
        private RectHV rectHV;
        private boolean flag;

        public Node(Point2D point2D, boolean flag, RectHV rectHV) {
            this.point2D = new Point2D(point2D.x(), point2D.y());
            this.flag = flag;
            this.left = null;
            this.right = null;
            this.rectHV = rectHV;
        }

        @Override
        public int compareTo(Node o) {
            if(flag) {
                if(this.point2D.x() > o.point2D.x()) return 1;
                else if(Math.abs(this.point2D.x() - o.point2D.x()) < 1e-20) return 0;
                else return -1;
            } else {
                if(this.point2D.y() > o.point2D.y()) return 1;
                else if(Math.abs(this.point2D.y() - o.point2D.y()) < 1e-20) return 0;
                else return -1;
            }
        }
    }

    private Point2D searchRec(Node root, Node p) {
        if(root.point2D.equals(p)) return root.point2D;
        if(root.compareTo(p) > 0) {
            if(root.left == null) return null;
            else return searchRec(root.left, p);
        } else {
            if(root.right == null) return null;
            else return searchRec(root.right, p);
        }
    }

    private boolean insertRec(Node root, Point2D p) {
        if(root.point2D.equals(p)) return false;
        Node newNode = new Node(p, !root.flag, null);
        if(root.compareTo(newNode) > 0) {
            RectHV newRect = null;
            if(root.flag) newRect = new RectHV(root.rectHV.xmin(), root.rectHV.ymin(), root.point2D.x(), root.rectHV.ymax());
            else newRect = new RectHV(root.rectHV.xmin(), root.rectHV.ymin(), root.rectHV.xmax(), root.point2D.y());
            if(root.left != null) return insertRec(root.left, p);
            else root.left = new Node(p, !root.flag, newRect);
            return true;
        } else {
            RectHV newRect = null;
            if(root.flag) newRect = new RectHV(root.point2D.x(), root.rectHV.ymin(), root.rectHV.xmax(), root.rectHV.ymax());
            else newRect = new RectHV(root.rectHV.xmin(), root.point2D.y(), root.rectHV.xmax(), root.rectHV.ymax());
            if(root.right != null) return insertRec(root.right, p);
            else root.right = new Node(p, !root.flag, newRect);
            return true;
        }
    }

    private void drawRec(Node root, double minX, double maxX, double minY, double maxY) {
        if(root == null) return;
        StdDraw.setPenRadius(.001);
        if (root.flag) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(root.point2D.x(), minY, root.point2D.x(), maxY);
            drawRec(root.left, minX, root.point2D.x(), minY, maxY);
            drawRec(root.right, root.point2D.x(), maxX, minY, maxY);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(minX, root.point2D.y(), maxX, root.point2D.y());
            drawRec(root.left, minX, maxX, minY, root.point2D.y());
            drawRec(root.right, minX, maxX, root.point2D.y(), maxY);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        root.point2D.draw();
    }

    private void rangeRec(Node root, RectHV rect, Set<Point2D> set) {
        if (root == null) return;
        if (root.rectHV.intersects(rect)) {
            if (rect.contains(root.point2D)) set.add(root.point2D);
            rangeRec(root.left, rect, set);
            rangeRec(root.right, rect, set);
        }
    }

    private Point2D nearestHelper(Node node, RectHV rect, double x, double y, Point2D nearestPointCandidate) {
        Point2D nearestPoint = nearestPointCandidate;

        if(node != null) {
            Point2D queryPoint = new Point2D(x, y);

            if(nearestPoint == null || queryPoint.distanceTo(nearestPoint) > rect.distanceSquaredTo(queryPoint)) {
                Point2D onePoint = new Point2D(node.point2D.x(), node.point2D.y());
                if(nearestPoint == null) {
                    nearestPoint = onePoint;
                } else {
                    if(queryPoint.distanceSquaredTo(nearestPoint) > queryPoint.distanceSquaredTo(onePoint)) {
                        nearestPoint = onePoint;
                    }
                }

                if(node.flag) {
                    if(x <= node.point2D.x()) {
                        if(node.left != null) nearestPoint = nearestHelper(node.left, node.left.rectHV, x, y, nearestPoint);
                        if(node.right != null) nearestPoint = nearestHelper(node.right, node.right.rectHV, x, y, nearestPoint);
                    } else {
                        if(node.right != null) nearestPoint = nearestHelper(node.right, node.right.rectHV, x, y, nearestPoint);
                        if(node.left != null) nearestPoint = nearestHelper(node.left, node.left.rectHV, x, y, nearestPoint);
                    }
                } else {
                    if(y <= node.point2D.y()) {
                        if(node.left != null) nearestPoint = nearestHelper(node.left, node.left.rectHV, x, y, nearestPoint);
                        if(node.right != null) nearestPoint = nearestHelper(node.right, node.right.rectHV, x, y, nearestPoint);
                    } else {
                        if(node.right != null) nearestPoint = nearestHelper(node.right, node.right.rectHV, x, y, nearestPoint);
                        if(node.left != null) nearestPoint = nearestHelper(node.left, node.left.rectHV, x, y, nearestPoint);
                    }
                }
            }
        }

        return nearestPoint;
    }

    public Point2D nearest(Point2D p) {
        return nearestHelper(root, new RectHV(0,0,1,1), p.x(), p.y(), null);
    }

    public KdTree() {
        this.root = null;
        this.numNodes = 0;
    }

    public boolean isEmpty() {
        return this.numNodes == 0;
    }

    public int size() {
        return this.numNodes;
    }

    public void insert(Point2D p) {
        if(this.numNodes == 0) {
            this.root = new Node(p, true, new RectHV(0,0,1,1));
            this.numNodes++;
        } else {
            if(insertRec(root, p)) this.numNodes++;
        }
    }

    public boolean contains(Point2D p) {
        // does the set contain the point p?
        if(isEmpty()) return false;
        return searchRec(root, new Node(p, true, null))!=null;
    }

    public void draw() {
        // draw all of the points to standard draw
        if (isEmpty()) return;
        drawRec(root, 0, 1, 0, 1);
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points in the set that are inside the rectangle
        Set<Point2D> pointList = new TreeSet<Point2D>();
        rangeRec(root, rect, pointList);
        return pointList;
    }



}
