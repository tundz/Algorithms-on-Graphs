
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConnectedComponents {
    static Set<Integer> visited;
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        visited = new HashSet<Integer>();
        for (int i = 0; i < adj.length; i++) {
             if (!visited.contains(i)) {
                 explore(adj, i);
                 result++;   
             }
            
        }
        return result;
    }

    
    private static void explore (ArrayList<Integer>[] adj, int x)  {
        visited.add(x);
       
         for(int i = 0; i < adj[x].size(); i++) {
              if(!visited.contains(adj[x].get(i)))
                   explore(adj, adj[x].get(i));
              
         }
            
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
        System.out.println(numberOfComponents(adj));
    }
}

