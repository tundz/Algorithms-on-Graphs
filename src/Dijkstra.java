import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
         
         HashSet<Integer> polled = new HashSet<Integer>();
         Entry[] minCost = new Entry[adj.length];
         for (int i = 0; i < minCost.length; i++)
             minCost[i] = new Entry(i, 2000000000);
         minCost[s].dist = 0;
         PriorityQueue<Entry> queue = new PriorityQueue<Entry>();
         for (int i = 0; i < minCost.length; i++)
             queue.add(minCost[i]);
         
         while (!queue.isEmpty()) {
             Entry u = queue.poll();
             if(!polled.contains(u.node)) {
                 polled.add(u.node);
                  for(int i = 0; i < adj[u.node].size(); i++) {
                      if (minCost[adj[u.node].get(i)].dist > minCost[u.node].dist + cost[u.node].get(i)) {
                             int newDist = minCost[u.node].dist + cost[u.node].get(i);
                             Entry newNodeEntry = new Entry (adj[u.node].get(i), newDist);
                             minCost[adj[u.node].get(i)] = newNodeEntry;
                             queue.add(newNodeEntry);
                      }
                  }
             }
             
         }
         if (minCost[t].dist == 2000000000)
              return -1;
         return (int)minCost[t].dist;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

class Entry implements Comparable<Entry>{
    int dist;
    int node;
    Entry (int v, int _dist) {
        dist = _dist;
        node = v;
    }
    @Override
    public int compareTo(Entry o) {
        return Integer.compare(dist, o.dist);
    }
    
}
 
