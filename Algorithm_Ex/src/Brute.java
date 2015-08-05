/**
 * Created by Kuilin on 7/5/2015.
 */

import java.util.Arrays;

public class Brute {
    private static void drawPoints(Point[] points) {
        for(Point p : points) {
            p.draw();
        }
    }

    private static void isLine(Point p, Point q, Point r, Point s) {
        double slopePq = p.slopeTo(q);
        double slopePr = p.slopeTo(r);
        double slopePs = p.slopeTo(s);

        if (slopePq == slopePr && slopePq == slopePs) {
            System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
            p.drawTo(s);
        }
    }

    private static void drawLine(Point[] points) {
        if(points == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }

        for(int i = 0; i < points.length; i++) {
            Point p = points[i];
            for(int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                for(int k = j + 1; k < points.length; k++) {
                    Point r = points[k];
                    for(int l = k + 1; l < points.length; l ++) {
                        Point s = points[l];
                        isLine(p, q, r, s);
                    }
                }
            }
        }
    }

    private static Point[] createPointArray(String filePath) {
        int[] intFlow = In.readInts(filePath);
        Point[] points = new Point[(intFlow.length - 1) / 2];
        int count = 0;
        for(int i = 0; i <intFlow.length - 1; i++) {
            if(i > 0 && i%2 != 0) {
                Point p = new Point(intFlow[i], intFlow[i + 1]);
                points[count] = p;
                count++;
            }
        }
        return points;
    }

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("requires file path as input");
        }

        String filePath = args[0];
        Point[] points = createPointArray(filePath);
        Arrays.sort(points);

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.005);

        drawPoints(points);

        drawLine(points);

    }


}
