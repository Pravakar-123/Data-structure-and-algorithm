import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> pairs = new ArrayList<>();

        // 1. Generate all pairs
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pairs.add(new int[]{nums1[i], nums2[j]});
            }
        }

        // 2. Sort pairs by sum
        pairs.sort((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        // 3. Pick first k pairs
        for (int i = 0; i < Math.min(k, pairs.size()); i++) {
            result.add(Arrays.asList(pairs.get(i)[0], pairs.get(i)[1]));
        }

        return result;
    }
}
