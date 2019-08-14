import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, double[] distance, int[] reachable, int[] shortest) {
         for(int i =0; i < adj.length; i++)
             distance[i] = Double.POSITIVE_INFINITY;
        distance[s] = 0;
         for(int i = 0; i < adj.length -1; i++) 
             relax(adj, cost, distance);
         
         Queue<Integer> ReachableFromInfiniteAbritrage = new LinkedList<Integer>();
         Set<Integer>   reachableFromInfiniteAbritrage = new HashSet<Integer>();
         for (int i = 0; i < adj.length; i++) {
             if(adj[i].size() != 0) {
                  for (int j = 0; j < adj[i].size(); j++) {
                       if (distance[adj[i].get(j)] > distance[i] + cost[i].get(j)) { 
                           distance[adj[i].get(j)] = distance[i] + cost[i].get(j);
                           ReachableFromInfiniteAbritrage.add(i);
                           ReachableFromInfiniteAbritrage.add(adj[i].get(j));
                       }
                           
                  }
             }
         }
         
         //Add all other vertices reachable from vertices relaxed on Vth iteration
         while (!ReachableFromInfiniteAbritrage.isEmpty()) {
             int v = ReachableFromInfiniteAbritrage.poll();
             distance[v] = Double.NEGATIVE_INFINITY; 
             reachableFromInfiniteAbritrage.add(v);
             for (Integer i : adj[v]) {
                 if(!reachableFromInfiniteAbritrage.contains(i))
                     ReachableFromInfiniteAbritrage.add(i);
                 
             }
         }
         
         for (int i = 0; i < adj.length; i++) {
             if (reachableFromInfiniteAbritrage.contains(i)) {
                 shortest[i] = 0;  
             }
             else
                 shortest[i] = 1;
             if (distance[i] == Double.POSITIVE_INFINITY)
                 reachable[i] = 0;
             else
                 reachable[i] = 1;
         }
         
    }

    private static boolean relax(ArrayList<Integer>[] graph, ArrayList<Integer>[] cost, double[] dist) {
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
        int s = scanner.nextInt() - 1;
        double distance[] = new double[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println((long)distance[i]);
            }
        }
    }

}

