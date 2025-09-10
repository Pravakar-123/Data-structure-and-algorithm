import java.util.*;

class Solution {
    public boolean isPossible(int[] nums) {
        // Map of number -> minHeap of subsequence lengths ending at that number
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num - 1)) {
                // extend shortest subsequence ending with num-1
                PriorityQueue<Integer> prevHeap = map.get(num - 1);
                int length = prevHeap.poll();
                if (prevHeap.isEmpty()) map.remove(num - 1);

                // add to current num
                map.computeIfAbsent(num, k -> new PriorityQueue<>()).offer(length + 1);
            } else {
                // start new subsequence
                map.computeIfAbsent(num, k -> new PriorityQueue<>()).offer(1);
            }
        }

        // check if all subsequences >= 3
        for (PriorityQueue<Integer> heap : map.values()) {
            for (int len : heap) {
                if (len < 3) return false;
            }
        }

        return true;
    }
}
