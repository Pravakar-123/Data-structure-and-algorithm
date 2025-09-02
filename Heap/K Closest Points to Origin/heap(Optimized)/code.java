import java.util.*;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap: compare by distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> distance(b) - distance(a)
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest
            }
        }

        int[][] result = new int[k][2];
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            result[idx++] = maxHeap.poll();
        }

        return result;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1]; // squared distance
    }
}
