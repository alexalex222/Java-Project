/**
 * Created by Kuilin on 6/28/2015.
 */
public class WeightedQuickUnionUF
{
    private int[] id;		// access to component id (site indexed)
    private int[] sz;		// size of components for roots (site indexed)
    private int count;		// number of components

    public WeightedQuickUnionUF (int N)
    {	// Initialize component id array
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++ )
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    private int find(int p)
    {
        // quick union
        while(p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q)
    {

        // find the root
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        // Make smaller root point to larger one
        if(sz[pRoot] < sz[qRoot])
        {
            id[pRoot] = qRoot;
            sz[qRoot] = sz[qRoot] + sz[pRoot];
        }
        else
        {
            id[qRoot] = pRoot;
            sz[pRoot] = sz[pRoot] + sz[qRoot];
        }
        count--;


    }

    public static void main(String[] args)
    {	//Solve dynamic connection problem in StdIn

        int N = StdIn.readInt();		// Read number of sites
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);					// Initialize N components
        while(!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();		// read pairs to connect
            if (uf.connected(p,q)) continue;		// ignore if already connected
            uf.union(p,q);		// combine pairs
            System.out.println(p + " " + q);		// print connect
        }

        System.out.println(uf.count() + "components");

    }
}
