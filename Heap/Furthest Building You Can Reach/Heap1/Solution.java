import java.util.*;

public class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Min-heap to track the climbs we use bricks for
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                minHeap.add(diff);

                // If we used more climbs than ladders, assign bricks to smallest climbs
                if (minHeap.size() > ladders) {
                    bricks -= minHeap.poll(); // take smallest climb for bricks
                }

                // If bricks go negative, we can't proceed
                if (bricks < 0) {
                    return i;
                }
            }
        }
        return heights.length - 1;
    }

}
