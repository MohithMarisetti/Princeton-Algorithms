import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class FastCollinearPoints {


    private Hashtable<String, ArrayList<String>> pointsList = new Hashtable<String, ArrayList<String>>();
    private Hashtable<String, Double> slopesList = new Hashtable<String, Double>();

    private LineSegment[] lineSegments;

    private int size = 0;
    private int slopeSize = 0;


    private void checkValidations(Point[] points) {


        // Check if any point is null
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        // Check if any point is duplicated
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }


    }

    private Point getPoint(String s) {
        s = s.substring(1, s.length() - 1);

        String[] arr = s.split(",");
        return new Point(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()));
    }

    private void createLineSegment(int count, int startPoint, Point[] points, int j) {


        Point[] p = new Point[count + 1];

        int it = 0;
        if (startPoint != 0) {
            p[it++] = points[0];

        }

        for (int z = startPoint; z <= (j - 1); z++) {

            p[it++] = points[z];

        }

        Arrays.sort(p);

        boolean isPresent = false;
        Point p1 = p[0];
        Point p2 = p[it - 1];
        LineSegment l = new LineSegment(p1, p2);


        ArrayList<String> temp = pointsList.get(p2.toString());

        if (temp != null) {

            double currentSlope = p2.slopeTo(p1);
            String p1_string = p1.toString();
            // For each element in arrayList get the point and calculate slope and compare it with currentSlope
            for (String str : temp) {

                if (str.equals(p1_string)) {
                    isPresent = true;
                    break;
                }

                if (p2.slopeTo(getPoint(str)) == currentSlope) {
                    isPresent = true;
                    break;
                }
            }

        }


        if (!isPresent) {
            if (lineSegments.length == size) {
                // Double the size of the array
                lineSegments = Arrays.copyOf(lineSegments, 2 * lineSegments.length);

            }


            if (temp == null) {
                temp = new ArrayList<String>();
                temp.add(p1.toString());
                pointsList.put(p2.toString(), temp);
            } else {
                temp.add(p1.toString());
            }


            lineSegments[size++] = l;


        }

    }


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {


        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point[] sortedPoints = points.clone();

        checkValidations(sortedPoints);

        Point[] points_copy;


        if (points.length < 4) {

            lineSegments = new LineSegment[0];
            return;

        }

        lineSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++) {

            // Remove points which are already used as origin
            points_copy = new Point[points.length - i];
            for (int c = i, x = 0; c < points.length; c++) {
                points_copy[x++] = sortedPoints[c];
            }

            if (points_copy.length < 4) {
                break;
            }

            Arrays.sort(points_copy, points_copy[0].slopeOrder());

            int j;
            double currentSlope;
            double prevSlope = points_copy[0].slopeTo(points_copy[1]);
            int count = 1;
            int startPoint = 0;
            for (j = 2; j < points_copy.length; j++) {
                currentSlope = points_copy[0].slopeTo(points_copy[j]);

                if (prevSlope != currentSlope) {
                    if (count >= 3) {

                        createLineSegment(count, startPoint, points_copy, j);
                    }
                    startPoint = j;
                    count = 1;
                    prevSlope = currentSlope;

                } else {
                    count++;

                }
            }
            if (count >= 3) {
                createLineSegment(count, startPoint, points_copy, j);
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
    /*
        try {
            File file = new File("./testcases/input80.txt");
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


            FastCollinearPoints collinear = new FastCollinearPoints(points);
            System.out.println(collinear.numberOfSegments());
            for (LineSegment segment : collinear.segments()) {
                System.out.println(segment);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

     */

    }


}
