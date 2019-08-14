
import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int[] dist = new int[adj.length];
        for(int i = 0; i < dist.length; i++)
            dist[i] = 2000000000;
        dist[0] = 0;
        for (int i = 0; i < adj.length - 1 ; i++)
            relax(adj, cost, dist);
        if (relax(adj, cost, dist))
           return 1;
        return 0;
    }
    
    private static boolean relax(ArrayList<Integer>[] graph, ArrayList<Integer>[] cost, int[] dist) {
        boolean anyRelaxed = false;
         for (int i = 0; i < graph.length; i++) {
               if (graph[i].size() != 0) {
                   for (int j = 0; j < graph[i].size(); j++){
                        if (dist[graph[i].get(j)] > dist[i] + cost[i].get(j)) {
                            dist[graph[i].get(j)] = dist[i] + cost[i].get(j);
                            anyRelaxed = true;           
                        }
                   }
               }
         }
         return anyRelaxed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}


