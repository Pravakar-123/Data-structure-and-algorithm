import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Step 1: Compute prefix sum for each row
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int count = 0;

        // Step 2: Fix colStart and colEnd
        for (int colStart = 0; colStart < n; colStart++) {
            for (int colEnd = colStart; colEnd < n; colEnd++) {

                // Map to count prefix sums
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1); // prefix sum 0 occurs once

                int currSum = 0;

                // Step 3: Iterate over each row and compute sum between colStart & colEnd
                for (int row = 0; row < m; row++) {
                    int rowSum = matrix[row][colEnd] - (colStart > 0 ? matrix[row][colStart - 1] : 0);
                    currSum += rowSum;

                    // If (currSum - target) exists in map, add its frequency
                    count += map.getOrDefault(currSum - target, 0);

                    // Record current prefix sum
                    map.put(currSum, map.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }
}
