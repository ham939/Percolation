
public class QuickUnionUF
{
    private int[] id, sz;

    // creates new object with n number of elements
    public QuickUnionUF(int N) throws IllegalArgumentException {

        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // gets the root of an element
    private int root(int i)
    {
        while (i != id[i]) i = id[i];
        return i;
    }

    //checks if two elements are connected
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    // connects two elements. makes their common root the one
    // that has the larger number of elements
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[i] += sz[j]; }

    }
}