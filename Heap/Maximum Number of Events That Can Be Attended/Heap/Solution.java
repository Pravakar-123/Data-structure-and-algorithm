import java.util.*;

public class Solution {
    public int maxEvents(int[][] events) {
        // Step 1: sort events by start day
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // stores endDay
        int i = 0, n = events.length, attended = 0;
        int day = 0;

        while (i < n || !minHeap.isEmpty()) {
            if (minHeap.isEmpty()) {
                // jump to next available event start
                day = events[i][0];
            }
            
            // Step 2: push all events starting today
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]);
                i++;
            }

            // Step 3: remove expired events
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Step 4: attend one event today (smallest endDay first)
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                attended++;
                day++;
            }
        }
        return attended;
    }

 
}
