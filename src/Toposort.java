
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Toposort {
    static Set explored;
    static ArrayList<Integer> order;
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        explored = new HashSet<Integer>();
        order = new ArrayList<Integer>(adj.length);
        for (int i = 0; i < adj.length; i++) {
            if(!explored.contains(i)) {
                explore (adj, i);
            }
        }
        Collections.reverse(order);
        //write your code here
        return order;
    }
    
    private static void explore (ArrayList<Integer>[] adj, int v)  {
        explored.add(v);
        for(int i = 0; i < adj[v].size(); i++) {
               if(!explored.contains(adj[v].get(i))) {
                    explore(adj, adj[v].get(i));
               }
        }   
        order.add(v);
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}


