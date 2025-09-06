import java.util.*;

public class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Max-heap for climbs covered by bricks
        PriorityQueue<Integer> bricksHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Min-heap for climbs covered by ladders (just for tracking)
        PriorityQueue<Integer> laddersHeap = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff > 0) {
                // Use bricks first
                bricks -= diff;
                bricksHeap.add(diff);

                // If bricks overflow, replace the biggest climb with a ladder
                if (bricks < 0) {
                    if (ladders > 0) {
                        int largestBrickClimb = bricksHeap.poll(); // take back biggest climb
                        laddersHeap.add(largestBrickClimb);
                        bricks += largestBrickClimb; // recover those bricks
                        ladders--;
                    } else {
                        return i; // cannot proceed
                    }
                }
            }
        }
        return heights.length - 1;
    }

  
}
