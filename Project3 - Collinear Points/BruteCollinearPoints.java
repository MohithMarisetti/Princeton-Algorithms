import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    private int size = 0;


    private void checkValidations(Point[] points) {

        // Exception checking

        if (points == null) {
            throw new IllegalArgumentException();
        }

        // Check if any point is null
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }


        Point[] temp = points.clone();
        // Check if any point is duplicated
        Arrays.sort(temp);

        for (int i = 1; i < temp.length; i++) {

            if (temp[i].compareTo(temp[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        checkValidations(points);

        lineSegments = new LineSegment[points.length];


        double slope1, slope2, slope3;

        for (int i = 0; i < points.length - 3; i++) {

            for (int j = i + 1; j < points.length - 2; j++) {


                slope1 = points[i].slopeTo(points[j]);


                for (int k = j + 1; k < points.length - 1; k++) {


                    slope2 = points[i].slopeTo(points[k]);
                    if (slope2 != slope1) {
                        continue;
                    }

                    for (int l = k + 1; l < points.length; l++) {

                        slope3 = points[i].slopeTo(points[l]);
                        if (slope3 != slope1) {
                            continue;
                        }
                        if ((slope1 == slope2) && (slope2 == slope3)) {
                            Point[] temp = new Point[4];
                            temp[0] = points[i];
                            temp[1] = points[j];
                            temp[2] = points[k];
                            temp[3] = points[l];
                            Arrays.sort(temp);

                            if (size == lineSegments.length) {
                                lineSegments = Arrays.copyOf(lineSegments, lineSegments.length * 2);
                            }
                            lineSegments[size++] = new LineSegment(temp[0], temp[3]);

                        }

                    }
                }
            }
        }


        lineSegments = Arrays.copyOf(lineSegments, size);


    }

    // the number of line segments
    public int numberOfSegments() {
        return size;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ls = lineSegments.clone();
        return ls;

    }


    public static void main(String[] args) {
        /* YOUR CODE HERE */

        try {
            File file = new File("./testcases/input40.txt");
            Scanner sc = new Scanner(file);
            int size = Integer.parseInt(sc.nextLine());
            Point[] points = new Point[size];


            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.trim();
                String[] arr = line.split("\\s+");

                if (arr[0].compareTo("") == 0 || arr[1].compareTo("") == 0) {
                    continue;
                }
                points[i++] = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));


            }


            BruteCollinearPoints collinear = new BruteCollinearPoints(points);

            System.out.println(collinear.numberOfSegments());
            for (LineSegment segment : collinear.segments()) {
                System.out.println(segment);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
