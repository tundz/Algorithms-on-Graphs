
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StronglyConnected {
    static int[] postOrder;
    static int index;
    
    //
    static Set<Integer> visited;
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjreverse) {
        postOrder = new int[adj.length];
        index = adj.length;
        Set<Integer> explored = new HashSet<Integer>();
        int numofSCCs = 0;
        
        //Using dfs on the reverse graph to find the sink
        //SCC of the original graph.
        //The first element of postOrder has the largest postOrder of the reverse graph
        //which means it is in the source SCC of reverse graph but in a sink SCC of
        //original graph
        for (int i = 0; i < adjreverse.length; i++) {
            if(!explored.contains(i)) {
                explore (adjreverse, i, explored);
            }
        }
        
        Set<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < postOrder.length; i++) {
            if(!visited.contains(postOrder[i])) {
                Explore(adj, postOrder[i], visited);
                numofSCCs++;
            }
        }
        return numofSCCs;
    }
    
    private static void explore (ArrayList<Integer>[] adj, int v, Set<Integer> explored)  {
        explored.add(v);
        for(int i = 0; i < adj[v].size(); i++) {
               if(!explored.contains(adj[v].get(i))) {
                    explore(adj, adj[v].get(i), explored);
               }
        }   
        index--;
        postOrder[index] = v;
     }
    
    private static void Explore (ArrayList<Integer>[] adj, int v, Set<Integer> explored)  {
        explored.add(v);
        for(int i = 0; i < adj[v].size(); i++) {
               if(!explored.contains(adj[v].get(i))) {
                    Explore(adj, adj[v].get(i), explored);
               }
        }   
        
     }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjreverse = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            adjreverse[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adjreverse[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adjreverse));
    }
}

