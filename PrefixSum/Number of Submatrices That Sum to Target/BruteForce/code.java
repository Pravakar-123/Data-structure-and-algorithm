

public class code {
    
}class Solution {
    public int numSubmatrixSumTargetBrute(int[][] nums, int target) {
        int m = nums.length;
        int n = nums[0].length;
        int ans = 0;

        // Loop through all possible top-left corners
        for (int rowStart = 0; rowStart < m; rowStart++) {
            for (int colStart = 0; colStart < n; colStart++) {

                // Loop through all possible submatrix sizes
                for (int rowSize = 1; rowStart + rowSize <= m; rowSize++) {
                    for (int colSize = 1; colStart + colSize <= n; colSize++) {

                        // Calculate the sum of current submatrix
                        int subSum = sumOfSubMatrix(nums, rowStart, rowSize, colStart, colSize);
                        if (subSum == target) ans++;
                    }
                }
            }
        }

        return ans;
    }

    // Helper function to calculate submatrix sum manually
    private int sumOfSubMatrix(int[][] nums, int rStart, int rSize, int cStart, int cSize) {
        int subMatrixSum = 0;

        for (int i = rStart; i < rStart + rSize; i++) {
            for (int j = cStart; j < cStart + cSize; j++) {
                subMatrixSum += nums[i][j];
            }
        }

        return subMatrixSum;
    }
}

