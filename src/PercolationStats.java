import java.util.Scanner;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class PercolationStats {

    private static final Scanner in = new Scanner(System.in);
    private Percolation grid;
    private int trials, means[], n;
    private double total, sqrTotal, mean, sitesOpened[], stdev = 0, confLo, confHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        this.n = n;
        this.trials = trials;
        sitesOpened = new double[trials];
        means = new int[trials];

        for (int i = 0; i < trials; i++) {

            grid = new Percolation(n);
            while (!grid.percolates())
                grid.open((int) (Math.random() * (n)) + 1, (int) (Math.random() * (n)) + 1);
            sitesOpened[i] = grid.getOpened() ;
            total += sitesOpened[i];
            //sqrTotal += (sitesOpened[i] * sitesOpened[i]);
        }

        mean = total / trials;
        mean /= (double) (n * n);

        for (int i = 0; i < trials; ++i)
            stdev += ((sitesOpened[i] / (double)(n * n) - mean) * (sitesOpened[i] / (double)(n * n) - mean));
        stdev /= (trials - 1);
        stdev = Math.sqrt(stdev);

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        //double var = sqrTotal - total * total;
        //stdev = Math.sqrt(var);
        return stdev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        confLo = mean - 1.96 * (stdev / Math.sqrt(trials));
        return confLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        confHi = mean + 1.96 * (stdev / Math.sqrt(trials));
        return confHi;
    }

    // test client (see below)
    public static void main(String[] args) {

        // n is the number of boxes on one side of the square
        // trials are the number of times we want to run Percolation code and
        // get the number of boxes opened

        int n = in.nextInt();
        int trials = in.nextInt();

        Stopwatch time = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("time = " + time.elapsedTime());
        System.out.println("mean = " + stats.mean());
        System.out.println("std dev = " + stats.stddev() + "\nconf intervals = [" + stats.confidenceLo() +
                ", " + stats.confidenceHi() + "]");


    }

}
