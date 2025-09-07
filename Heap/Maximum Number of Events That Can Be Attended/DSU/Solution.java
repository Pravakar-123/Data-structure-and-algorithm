import java.util.*;
public class Solution {
    private Map<Integer, Integer> parent = new HashMap<>();

    // DSU: find next available day
    private int find(int day) {
        if (!parent.containsKey(day)) return day;
        int next = find(parent.get(day));
        parent.put(day, next); // path compression
        return next;
    }

    public int maxEvents(int[][] events) {
        // Sort by endDay first, then startDay
        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int count = 0;
        for (int[] event : events) {
            int available = find(event[0]); // earliest free day ≥ start
            if (available <= event[1]) {
                count++;
                parent.put(available, available + 1); // mark day as used
            }
        }
        return count;
    }

}