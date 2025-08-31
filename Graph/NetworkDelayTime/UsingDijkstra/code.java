package Graph.NetworkDelayTime.UsingDijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build graph (adjacency list)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] t : times) {
            graph.computeIfAbsent(t[0], x -> new ArrayList<>()).add(new int[] { t[1], t[2] });
        }

        // Step 2: Min-heap for Dijkstra (time, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] { 0, k });

        // Step 3: Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Step 4: Dijkstra
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0], node = cur[1];

            if (time > dist[node])
                continue;

            if (!graph.containsKey(node))
                continue;

            for (int[] nei : graph.get(node)) {
                int next = nei[0], w = nei[1];
                if (time + w < dist[next]) {
                    dist[next] = time + w;
                    pq.offer(new int[] { dist[next], next });
                }
            }
        }

        // Step 5: Find max delay
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}
