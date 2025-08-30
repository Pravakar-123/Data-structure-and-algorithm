import java.util.*;

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Step 1: generate all pair distances
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(nums[i] - nums[j]);
                maxHeap.offer(dist);

                // keep only k smallest
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        // Step 2: root of maxHeap = k-th smallest distance
        return maxHeap.peek();
    }
}

