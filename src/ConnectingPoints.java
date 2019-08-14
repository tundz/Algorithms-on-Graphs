import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        ArrayList<Edge> edgeList = makeEdges(x, y);
        Collections.sort(edgeList);
        DisjointUnionSets newSet = new DisjointUnionSets(x.length);
        
        for (int i = 0; i < edgeList.size(); i++) {
            Edge currEdge = edgeList.get(i);
            if (newSet.find(currEdge.vertex1) != newSet.find(currEdge.vertex2)) {
                result += currEdge.dist;
                newSet.union(currEdge.vertex1, currEdge.vertex2);
            }
        }
        
        return result;
    }
    
    private static ArrayList<Edge> makeEdges(int[]x, int[]y) {
        int n = x.length - 1;
        n = ((n + 1) * n) / 2;
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        for (int i = 0; i < x.length; i++) {
             for (int j = i + 1; j < x.length; j++) {
                 double dist = Math.sqrt((x[i]-x[j])*(x[i] - x[j]) + (y[i] - y[j]) * (y[i]- y[j]));
                 edgeList.add(new Edge(i, j, dist));
             }
        }
        return edgeList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

class Edge implements Comparable<Edge>{
    int vertex1;
    int vertex2;
    double dist;
    
    Edge(int i, int e, double _dist) {
        vertex1 = i;
        vertex2 = e;
        dist = _dist;
    }
    
    public int compareTo (Edge other) {
        return Double.compare(dist, other.dist);
    }
}
