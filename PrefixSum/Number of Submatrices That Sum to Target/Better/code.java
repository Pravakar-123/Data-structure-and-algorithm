class Solution {
    public int numSubmatrixSumTargetPrefixRow(int[][] nums, int target) {
        int m = nums.length;
        int n = nums[0].length;
        int ans = 0;

        // Step 1: Compute prefix sum row-wise
        for (int row = 0; row < m; row++) {
            for (int col = 1; col < n; col++) {
                nums[row][col] += nums[row][col - 1];
            }
        }

        // Step 2: Fix two column boundaries
        for (int colStart = 0; colStart < n; colStart++) {
            for (int colEnd = colStart; colEnd < n; colEnd++) {

                // Now treat rows as 1D array of sums between colStart..colEnd
                for (int rowStart = 0; rowStart < m; rowStart++) {
                    int sum = 0;
                    for (int rowEnd = rowStart; rowEnd < m; rowEnd++) {
                        // range sum for this row from colStart..colEnd
                        sum += nums[rowEnd][colEnd] - (colStart > 0 ? nums[rowEnd][colStart - 1] : 0);
                        if (sum == target) ans++;
                    }
                }
            }
        }

        return ans;
    }
}
