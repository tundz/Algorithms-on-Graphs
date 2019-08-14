
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Acyclicity {
    static Set<Integer> explored;
    static int[] postOrder;
    static int order = 0;
    private static int acyclic(ArrayList<Integer>[] adj) {
        explored = new HashSet<Integer>();
        postOrder = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if(!explored.contains(i)) {
                explore (adj, i);
            }
        }
         for (int i = 0; i < adj.length; i++) {
             
              for (int j = 0; j < adj[i].size(); j++) {
                  if (postOrder[i] < postOrder[adj[i].get(j)])
                      return 1;
              }
         }
         return 0;
    }

    private static void explore (ArrayList<Integer>[] adj, int v)  {
       explored.add(v);
       for(int i = 0; i < adj[v].size(); i++) {
              if(!explored.contains(adj[v].get(i))) {
                   explore(adj, adj[v].get(i));
              }
       }   
       postOrder[v] = order + 1;
       order = order + 1;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}


