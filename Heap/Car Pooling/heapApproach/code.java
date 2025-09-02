import java.util.*;

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // 1. Sort by start location
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        // Min-heap ordered by end location
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        int currentPassengers = 0;

        // 2. Iterate trips
        for (int[] trip : trips) {
            int num = trip[0];
            int start = trip[1];
            int end = trip[2];

            // Remove all trips that ended before current trip starts
            while (!minHeap.isEmpty() && minHeap.peek()[2] <= start) {
                currentPassengers -= minHeap.poll()[0];
            }

            // Add this trip
            currentPassengers += num;
            if (currentPassengers > capacity) {
                return false;
            }

            // Push trip into heap
            minHeap.offer(trip);
        }

        return true;
    }
}
