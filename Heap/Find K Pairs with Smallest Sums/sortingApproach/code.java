import java.util.*;
//Heap appraoch 1(Brute forc)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-heap storing (sum, i, j)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Generate all pairs and push to heap
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                minHeap.offer(new int[]{sum, i, j});
            }
        }

        // Extract k smallest pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int i = top[1], j = top[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
        }

        return result;
    }
}


//Heap appraoch 2(Brute force)
class Solution1 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-heap: (sum, i, j)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));

        // Initialize heap with pairs (nums1[i], nums2[0])
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Extract k smallest
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int sum = top[0], i = top[1], j = top[2];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Push next pair with nums2[j+1]
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }
}





