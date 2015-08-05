/**
 * Created by Kuilin on 6/28/2015.
 */
public class Percolation {

    private int gridSize;

    private WeightedQuickUnionUF linearGrid;

    private boolean[][] grid;

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        this.gridSize = N;
        // create a N*N+2 array; 0 serves as virtual top; N*N+1 serves as virtual bottom
        linearGrid = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        grid = new boolean[gridSize][gridSize];
    }

    public void open(int i, int j) {
        // open site (row i, column j) if it is not already
        validate(i, j);
        if (!isOpen(i, j)) {
            grid[i - 1][j - 1] = true;

            unite(i, j, i - 1, j);
            unite(i, j, i + 1, j);
            unite(i, j, i, j - 1);
            unite(i, j, i, j + 1);

            if (i == 1) { // connect to virtual top site
                linearGrid.union(0, xyTo1D(i, j));
                // linearGridNoBackwash.union(0, xyTo1D(i, j));
            }
            if (i == gridSize) { // connect to virtual bottom site
                linearGrid.union(gridSize * gridSize + 1, xyTo1D(i, j));
            }
        }
    }

    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        validate(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        // is site (row i, column j) full?
        validate(i, j);
        return linearGrid.connected(0, xyTo1D(i, j));
    }

    public boolean percolates() {
        // does the system percolate?
        return linearGrid.connected(0, gridSize * gridSize + 1);
    }

    private int xyTo1D(int i, int j) {
        // convert the 2D coordinates to 1d array index
        validate(i, j);
        return (i - 1) * gridSize + j;
    }

    private void unite(int i, int j, int m, int n) { // 1-based coordinates
        if (m > 0 && n > 0 && m <= gridSize && n <= gridSize && isOpen(m, n)) {
            linearGrid.union(xyTo1D(i, j), xyTo1D(m, n));
        }
    }

    private void validate(int i, int j) {
        int x = i - 1;
        int y = j - 1;
        if (x < 0 || y < 0 || x >= gridSize || y >= gridSize) {
            throw new IndexOutOfBoundsException(String.format(
                    "Indexes i=%s and j=%s are out of bounds!", i, j));
        }
    }

    public static void main(String[] args){
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system

        final int DELAY = 1;

        // turn on animation mode
        StdDraw.show(0);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);
        
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            
        }

        System.out.println("Does percolate?");
        System.out.println(perc.percolates());
        System.out.println("Is full?");
        System.out.println(perc.isFull(3,6));

    }
}
