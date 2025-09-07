
// Brute force

import java.util.*;

public class Solution {
    public int maxEvents(int[][] events) {
        // Sort by endDay first, then startDay
        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        Set<Integer> booked = new HashSet<>(); // stores occupied days
        int count = 0;

        for (int[] event : events) {
            int start = event[0];
            int end = event[1];

            for (int d = start; d <= end; d++) {
                if (!booked.contains(d)) {
                    booked.add(d);
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}