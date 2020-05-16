/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point


    private class BySlope implements Comparator<Point> {

        public int compare(Point point1, Point point2) {


            double slope1 = slopeTo(point1);
            double slope2 = slopeTo(point2);
            if (slope1 == Double.POSITIVE_INFINITY) {
                if (slope2 == Double.POSITIVE_INFINITY) {
                    return 0;
                } else if (slope2 == Double.NEGATIVE_INFINITY) {
                    return +1;
                }
            }
            if (slope2 == Double.POSITIVE_INFINITY) {
                if (slope1 == Double.NEGATIVE_INFINITY) {
                    return -1;
                }
            }
            if (slope1 == Double.NEGATIVE_INFINITY && slope2 == Double.NEGATIVE_INFINITY) {
                return 0;
            }
            if (slope1 < slope2) {
                return -1;
            }
            if (slope1 == slope2) {
                return 0;
            }
            return 1;


        }

    }


    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */

        if (that == null) {
            throw new NullPointerException();
        }

        double numerator = that.y - this.y;
        double denominator = that.x - this.x;

        if (numerator == 0) {
            if (denominator == 0) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return 0.0;
            }
        } else if (numerator != 0) {
            if (denominator != 0) {
                return (numerator / denominator);
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */

        if (this.y < that.y) {
            return this.y - that.y;
        }
        if (this.y == that.y) {
            return this.x - that.x;
        }
        return (this.y - that.y);
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new BySlope();

    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        /*

        try {
            File file = new File("./test/input9.txt");
            Scanner sc = new Scanner(file);
            int size = Integer.parseInt(sc.nextLine());
            Point[] points = new Point[size];

            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(" ");
                points[i++] = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));

            }
            Point[] points_copy = points;


            for (int z = 0; z < size; z++) {
                points = points_copy;
                Arrays.sort(points, points_copy[z].BY_SLOPE);
                for (i = 0; i < size; i++) {
                    System.out.print(points_copy[i]);
                }
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        System.out.println(Double.NEGATIVE_INFINITY - Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY - Double.NEGATIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY - Double.NEGATIVE_INFINITY);
        System.out.println(10.0 - Double.NEGATIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY - 10.0);
        System.out.println(10.0 - Double.POSITIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY - 10.0);


         */
    }


}
