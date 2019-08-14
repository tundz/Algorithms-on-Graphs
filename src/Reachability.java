
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reachability {
    //static boolean isReachable;
    static Set<Integer> visited;
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        visited = new HashSet<Integer>();
        if (explore(adj, x, y))
            return 1;
        return 0;
    }

    private static boolean explore (ArrayList<Integer>[] adj, int x, int y)  {
        boolean isReachable = false;
        visited.add(x);
        if(x == y)
            return  true;
        
         for(int i = 0; i < adj[x].size(); i++) {
                if(!visited.contains(adj[x].get(i)))
                     isReachable = explore(adj, adj[x].get(i), y);
                if (isReachable)
                    return true;
         }
            
         return false;
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
        System.out.println(reach(adj, x, y));
    }
}

