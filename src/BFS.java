
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int[] dist = new int[adj.length];
        Queue<Integer> toexplore = new LinkedList<Integer>();
        toexplore.add(s);
        for(int i = 0; i < dist.length; i++)
            dist[i] = adj.length + 1;
        dist[s] = 0;
        while (!toexplore.isEmpty()) {
            
            int explore = toexplore.poll();
            for(int i = 0; i < adj[explore].size(); i++) {
                if (dist[adj[explore].get(i)] == adj.length + 1) {
                    toexplore.add(adj[explore].get(i));
                    dist[adj[explore].get(i)] = dist[explore] + 1;
                }
            }
            
        }
        
        if (dist[t] == adj.length + 1)
            return -1;
        return dist[t];
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

