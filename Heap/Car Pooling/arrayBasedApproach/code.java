
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // 1. Difference array for passenger changes
        int[] changes = new int[1001];  // since 0 <= from < to <= 1000

        // 2. Record pick-ups and drop-offs
        for (int[] trip : trips) {
            int num = trip[0];
            int from = trip[1];
            int to = trip[2];

            changes[from] += num;  // pick up passengers
            changes[to]   -= num;  // drop off passengers
        }

        // 3. Sweep through locations
        int currentPassengers = 0;
        for (int i = 0; i <= 1000; i++) {
            currentPassengers += changes[i];
            if (currentPassengers > capacity) {
                return false; // capacity exceeded
            }
        }

        return true; // all good
    }
}