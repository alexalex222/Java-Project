/**
 * Created by Kuilin on 7/5/2015.
 */

public class UF
{
    private int[] id;		// access to component id (site indexed)
    private int count;		// number of components

    public UF (int N)
    {	// Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++ )
        {
            id[i] = i;
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
        // // quick find
        // return id[p];

        // quick union
        while(p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q)
    {
        // // quick find
        // int pID = find(p);
        // int qID = find(q);
        // if(pID == qID) return;
        // for(int i = 0; i < id.length; i++)
        // {
        // if(id[i] == pID) id[i] = qID;
        // }
        // count--;

        // quick union
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;


    }

    public static void main(String[] args)
    {	//Solve dynamic connection problem in StdIn

        int N = StdIn.readInt();		// Read number of sites
        UF uf = new UF(N);					// Initialize N components
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
