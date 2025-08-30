import java.util.*;

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = nums[n - 1] - nums[0]; // max possible distance

        while (low < high) {
            int mid = (low + high) / 2;

            if (countPairs(nums, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Count pairs with distance <= guess
    private int countPairs(int[] nums, int guess) {
        int count = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > guess) {
                left++;
            }
            count += right - left;
        }

        return count;
    }
}
