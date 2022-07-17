import java.util.Scanner;

public class Percolation {

    public static final Scanner in = new Scanner(System.in);

    private int n = 0;
    private int numSites, numOpen = 0, opened = 0;
    private int[] tempSites;
    private static QuickUnionUF sites;
    //private static Grid grid;

    // creates n-by-n grid, with all sites initially blocked
    // 0 for blocked. 1 for open
    public Percolation(int n) {

        this.numSites = n*n+2;
        this.n = n;
        sites = new QuickUnionUF(numSites);
        tempSites = new int[numSites];
        //grid = new Grid(n);
        //grid.showGrid();
    }

    // return the amount of times a box has been opened

    public int getOpened() {
        return opened;
    }

    // opens the site (row, col) if it is not open already.
    // give the site value 1
    public void open(int row, int col) {

        // if not open
        if (!isOpen(row, col)) {
            tempSites[row - 1 + (col - 1) * n] = 1;
            opened++;


            // check every other site connected

            // if the site is not at the utmost top. connect it with the upper site if open
            // otherwise, connect it with the top virtual site.
            if (row == 1)
                sites.union(row - 1 + (col - 1) * n, n * n);
            else if (isOpen(row - 1, col))
                sites.union(row - 1 + (col - 1) * n, row - 2 + (col - 1) * n);

            // if the site is not at the utmost bot, connect it with the lower site,
            // otherwise connect it with the bot virtual site.
            if (row == n)
                sites.union(row - 1 + (col - 1) * n, n * n + 1);
            else if (isOpen(row + 1, col))
                sites.union(row - 1 + (col - 1) * n, row + (col - 1) * n);


            // if the site is not at the utmost left
            if (col != 1 && isOpen(row, col - 1))
                sites.union(row - 1 + (col - 1) * n, row - 1 + (col - 2) * n);

            // if the site is not at the utmost right
            if (col != n && isOpen(row, col + 1))
                sites.union(row - 1 + (col - 1) * n, row - 1 + (col) * n);

            //grid.openCell(row, col);
            //grid.showGrid();
            System.out.println();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (tempSites[row - 1 + (col - 1) * n] == 1) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (sites.connected(row - 1 + (col - 1) * n, n * n ))
            return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {

        return sites.connected(n * n, n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

        System.out.println("Enter n for the n x n grid");
        int n;
        Percolation grid = new Percolation(n = in.nextInt());

        grid.open(1, 3);
        grid.open(2, 3);
        grid.open(3, 3);
        grid.open(4, 3);
        //grid.open(4, 2);
        //grid.open(1,2);

        System.out.println(grid.percolates());
    }
}
