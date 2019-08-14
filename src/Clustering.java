import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        int clusters = x.length;
        ArrayList<Edge> edgeList = makeEdges(x, y);
        Collections.sort(edgeList);
        DisjointUnionSets newSet = new DisjointUnionSets(x.length);
        
        for (int i = 0; i < edgeList.size(); i++) {
            Edge currEdge = edgeList.get(i);
            if (newSet.find(currEdge.vertex1) != newSet.find(currEdge.vertex2)) {
                if (clusters > k) {
                    newSet.union(currEdge.vertex1, currEdge.vertex2);
                    clusters--;
                }
                else if (clusters == k)
                    return currEdge.dist;
            }
        }
        
        return -1;
    }

    private static ArrayList<Edge> makeEdges(int[]x, int[]y) {
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        for (int i = 0; i < x.length; i++) {
             for (int j = i + 1; j < x.length; j++) {
                 double dist = Math.sqrt((x[i]-x[j])*(x[i] - x[j]) + (y[i] - y[j]) * (y[i]- y[j]));
                 edgeList.add(new Edge(i, j, dist));
             }
        }
        return edgeList;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
        scanner.close();
    }
    
    
}

class DisjointUnionSets
{
    int[] rank, parent;
    int n;
 
    // Constructor
    public DisjointUnionSets(int n)
    {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }
 
    // Creates n sets with single item in each
    void makeSet()
    {
        for (int i=0; i<n; i++)
        {
            // Initially, all elements are in
            // their own set.
            parent[i] = i;
        }
    }
 
    // Returns representative of x's set
    int find(int x)
    {
        // Finds the representative of the set
        // that x is an element of
        if (parent[x]!=x)
        {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            parent[x] = find(parent[x]);
 
            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }
 
        return parent[x];
    }
 
    // Unites the set that includes x and the set
    // that includes x
    void union(int x, int y)
    {
        // Find representatives of two sets
        int xRoot = find(x), yRoot = find(y);
 
        // Elements are in the same set, no need
        // to unite anything.
        if (xRoot == yRoot)
            return;
 
         // If x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot])
 
            // Then move x under y  so that depth
            // of tree remains less
            parent[xRoot] = yRoot;
 
        // Else if y's rank is less than x's rank
        else if (rank[yRoot] < rank[xRoot])
 
            // Then move y under x so that depth of
            // tree remains less
            parent[yRoot] = xRoot;
 
        else // if ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[yRoot] = xRoot;
 
            // And increment the the result tree's
            // rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
