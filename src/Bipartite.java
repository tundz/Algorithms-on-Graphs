
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    
    private static int bipartite(ArrayList<Integer>[] adj) {
        boolean isBipartite = true;
        int[] dist = new int[adj.length];
        Queue<Integer> explore = new LinkedList<Integer>();
        explore.add(0);
        for(int i = 0; i < dist.length; i++)
            dist[i] = adj.length + 1;
        dist[0] = 0;
        while (!explore.isEmpty()) {
            
            int toexplore = explore.poll();
            for(int i = 0; i < adj[toexplore].size(); i++) {
                if (dist[toexplore] == dist[adj[toexplore].get(i)]) {
                    isBipartite = false;
                    break;
                }
                if (dist[adj[toexplore].get(i)] == adj.length + 1) {
                    explore.add(adj[toexplore].get(i));
                    dist[adj[toexplore].get(i)] = dist[toexplore] + 1;
                }
            }
            
        }
        if (isBipartite)
            return 1;
        return 0;
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
        System.out.println(bipartite(adj));
    }
}


