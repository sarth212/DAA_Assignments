// Name:Sarthak Deshmukh
// PRN:124B2F004

import java.util.*;
class Edge {
    int to; double weight;
    Edge(int to, double weight) { this.to = to; this.weight = weight; }
}
public class Assignment4 {
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    public static double[] dijkstra(int src, int n) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); int u = cur[0]; double d = cur[1];
            if (d > dist[u]) continue;
            for (Edge e : graph.getOrDefault(u, new ArrayList<>())) {
                if (dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.add(new int[]{e.to, (int) dist[e.to]});
                }
            }
        }
        return dist;
    }
    public static void updateEdge(int from, int to, double w) {
        for (Edge e : graph.getOrDefault(from, new ArrayList<>())) if (e.to == to) { e.weight = w; break; }
    }
    public static void main(String[] args) {
        int n = 6;
        graph.put(0, Arrays.asList(new Edge(1,4), new Edge(2,2)));
        graph.put(1, Arrays.asList(new Edge(2,5), new Edge(3,10)));
        graph.put(2, Arrays.asList(new Edge(4,3)));
        graph.put(3, Arrays.asList(new Edge(5,11)));
        graph.put(4, Arrays.asList(new Edge(3,4)));
        graph.put(5, new ArrayList<>());
        int ambulance = 0, hospital = 5;
        double[] dist = dijkstra(ambulance, n);
        System.out.println("Initial shortest time: " + dist[hospital] + " minutes");
        System.out.println("\nTraffic update: road 2->4 now slower (weight 10)");
        updateEdge(2,4,10);
        dist = dijkstra(ambulance, n);
        System.out.println("Updated shortest time: " + dist[hospital] + " minutes");
    }
}

