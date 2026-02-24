import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final int topVirtualSite;
    private final int bottomVirtualSite;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.topVirtualSite = n * n;
        this.bottomVirtualSite = n * n + 1;
        this.openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int siteIndex = xyTo1D(row, col);

            if (row == 1) {
                uf.union(siteIndex, topVirtualSite);
            }
            if (row == n) {
                uf.union(siteIndex, bottomVirtualSite);
            }

            // check and union with neighbors
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(siteIndex, xyTo1D(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(siteIndex, xyTo1D(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(siteIndex, xyTo1D(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(siteIndex, xyTo1D(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(xyTo1D(row, col)) == uf.find(topVirtualSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topVirtualSite) == uf.find(bottomVirtualSite);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and " + n);
        }
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * n + (col - 1);
    }
}
