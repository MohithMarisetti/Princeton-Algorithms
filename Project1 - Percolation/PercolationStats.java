/* *****************************************************************************
 *  Name:    Mohith Marisetti
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percolationThresholds;
    private double squareRootOfT;
    private final static double CONFIDENCE_95 = 1.96;
    private double mean = 0, stddev = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }


        percolationThresholds = new double[trials];
        squareRootOfT = Math.sqrt(trials);
        int counter = 0;

        for (int i = 1; i <= trials; i++) {

            Percolation p = new Percolation(n);


            while (!p.percolates()) {

                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                p.open(row, col);


            }

            percolationThresholds[counter] = ((double) p.numberOfOpenSites() / (double) (n * n));
            counter += 1;


        }


    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(percolationThresholds);
        return mean;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(percolationThresholds);
        return stddev;
    }


    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (mean == 0) {
            mean = mean();
        }
        if (stddev == 0) {
            stddev = stddev();
        }
        return (mean - ((CONFIDENCE_95 * stddev) / squareRootOfT));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (mean == 0) {
            mean = mean();
        }
        if (stddev == 0) {
            stddev = stddev();
        }
        return (mean + ((CONFIDENCE_95 * stddev) / squareRootOfT));
    }

    // test client (see below)
    public static void main(String[] args) {
    }


}
