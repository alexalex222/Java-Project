/**
 * Created by Kuilin on 6/28/2015.
 */
public class PercolationStats {

    private double[] thresholds;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        validate(N, T);

        thresholds = new double[T];
        long threshold_count;
        int x = 0;
        int y = 0;
        for(int i = 0; i < T; i++){
            Percolation myPercolation = new Percolation(N);
            threshold_count = 0;
            while(!myPercolation.percolates()){
                x = StdRandom.uniform(1, N+1);
                y = StdRandom.uniform(1, N+1);
                if(!myPercolation.isOpen(x, y)){
                    myPercolation.open(x, y);
                    threshold_count++;
                }
            }
            thresholds[i] = (double) threshold_count/(double) (N*N);
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        if(thresholds.length > 1){
            return StdStats.stddev(thresholds);
        }
        else{
            return Double.NaN;
        }
    }

    public double confidenceLo(){
        // low  endpoint of 95% confidence interval
        if(thresholds.length > 1){
            return this.mean() - 1.96*this.stddev()/(Math.sqrt(thresholds.length));
        }
        else {
            return Double.NaN;
        }
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        if(thresholds.length > 1) {
            return  this.mean() + 1.96*this.stddev()/(Math.sqrt(thresholds.length));
        }
        else {
            return Double.NaN;
        }
    }

    private void validate(int N, int T) {
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Invalid N = %s and/or T = %s", N, T));
        }

    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);
        double mean = stats.mean();
        double stddev = stats.stddev();
        double hilim = stats.confidenceHi();
        double lolim = stats.confidenceLo();

        System.out.println("mean = " + mean);
        System.out.println("stddev = " + stddev);
        System.out.println("95% confidence interval = " + hilim + " , " + lolim);
    }
}
